package dit.hua.team50.BloodDonor.controller;

import dit.hua.team50.BloodDonor.entity.Application;
import dit.hua.team50.BloodDonor.entity.Citizen;
import dit.hua.team50.BloodDonor.entity.User;
import dit.hua.team50.BloodDonor.payload.request.ApplicationRequest;
import dit.hua.team50.BloodDonor.repository.UserRepository;
import dit.hua.team50.BloodDonor.service.ApplicationService;
import dit.hua.team50.BloodDonor.service.BloodUserDetailsService;
import dit.hua.team50.BloodDonor.service.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDate.now;

@RestController
@RequestMapping("/citizen")
public class CitizenController {
    @Autowired
    private BloodUserDetailsService userService;

    @Autowired
    private CitizenService citizenService;

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("")
    public Citizen CitizenRedirect(@AuthenticationPrincipal UserDetails userDetails){
        Long user_id = userService.getLoggedInUserId(userDetails);
        return citizenService.getCitizenByUserId(user_id);

    }

    //show applications
    @GetMapping("/{citizen_id}")
    public List<Application> myApplications(@PathVariable Integer citizen_id) {

        return citizenService.getMyApplications(citizen_id);
    }

    @PostMapping("/{citizen_id}/new")
    public Application newApplication(@PathVariable Integer citizen_id, @RequestBody ApplicationRequest applicationRequest){
        Citizen citizen = citizenService.getCitizen(citizen_id);
        return applicationService.addApplication(citizen, applicationRequest);
    }

    //show profile
    @GetMapping("/{citizen_id}/profile")
    public Citizen citizenInformation(@PathVariable Integer citizen_id) {
        return citizenService.getCitizen(citizen_id);
    }

//    @GetMapping("/{citizen_id}/profile/edit")
//    public String editCitizenInformation(@PathVariable Integer citizen_id, @ModelAttribute("citizen information") Citizen citizenInfo, Model model){
//        return "edit_citizen_info";
//    }

    @PostMapping("/{citizen_id}/profile/edit")
    public String saveCitizenInformation(@PathVariable Integer citizen_id,@ModelAttribute("citizen information") Citizen citizenInfo, Model model){
        citizenService.updateCitizen(citizenInfo);
        model.addAttribute("updated citizen information", citizenInfo);
        return "citizen_info";
    }

}
