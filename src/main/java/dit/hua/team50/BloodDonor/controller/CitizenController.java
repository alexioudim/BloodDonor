package dit.hua.team50.BloodDonor.controller;

import dit.hua.team50.BloodDonor.entity.Application;
import dit.hua.team50.BloodDonor.entity.Citizen;
import dit.hua.team50.BloodDonor.entity.User;
import dit.hua.team50.BloodDonor.payload.request.ApplicationRequest;
import dit.hua.team50.BloodDonor.payload.request.ContactInfoRequest;
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
    public Citizen CitizenInfo(@AuthenticationPrincipal UserDetails userDetails){
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

    @PutMapping("/{citizen_id}/edit")
    public Citizen saveCitizenInformation(@PathVariable Integer citizen_id, @RequestBody ContactInfoRequest citizenInfo){
        Citizen citizen = citizenService.getCitizen(citizen_id);

        citizen.setPhone_number(citizenInfo.getPhone_number());
        citizen.setEmail(citizenInfo.getEmail());
        return citizenService.updateCitizen(citizen);
    }

}
