package dit.hua.team50.BloodDonor.controller;

import dit.hua.team50.BloodDonor.entity.Application;
import dit.hua.team50.BloodDonor.entity.Citizen;
import dit.hua.team50.BloodDonor.entity.User;
import dit.hua.team50.BloodDonor.repository.UserRepository;
import dit.hua.team50.BloodDonor.service.ApplicationService;
import dit.hua.team50.BloodDonor.service.CitizenService;
import dit.hua.team50.BloodDonor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static java.time.LocalDate.now;

@Controller
@RequestMapping("/citizen")
public class CitizenController {
    @Autowired
    private UserService userService;

    @Autowired
    private CitizenService citizenService;

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("")
    public String CitizenRedirect(@AuthenticationPrincipal User user, Model model){
        Citizen citizen = citizenService.getCitizenByUserId(user.getId());
        model.addAttribute("citizen", citizen);

        return "redirect";
    }

    @GetMapping("/{citizen_id}")
    public String myApplications(@PathVariable Integer citizen_id, Model model) {

        List<Application> myApplications = citizenService.getMyApplications(citizen_id);
        model.addAttribute("myApplications", myApplications);

        return "my_applications";
    }

    @GetMapping("/{citizen_id}/new")
    public String newApplication(@PathVariable Integer citizen_id, Model model){

        Application application = new Application();
        application.setDate_created(now());
        model.addAttribute("newApplication", application );

        return "add_application";
    }

    @PostMapping("/{citizen_id}/new")
    public String saveApplication(@ModelAttribute("newApplication") Application application, @PathVariable Integer citizen_id, Model model) {
        application.setApprovalStatus("Pending");
        applicationService.saveApplication(application);
        model.addAttribute("applications", citizenService.getMyApplications(citizen_id));

        return "my_applications";
    }

    @GetMapping("/{citizen_id}/profile")
    public String citizenInformation(@PathVariable Integer citizen_id, Model model){
        Citizen citizenInfo = citizenService.getCitizen(citizen_id);
        model.addAttribute("citizen information", citizenInfo);
        return "citizen_info";
    }

    @GetMapping("/{citizen_id}/profile/edit")
    public String editCitizenInformation(@PathVariable Integer citizen_id, @ModelAttribute("citizen information") Citizen citizenInfo, Model model){
        return "edit_citizen_info";
    }

    @PostMapping("/{citizen_id}/profile/edit")
    public String saveCitizenInformation(@PathVariable Integer citizen_id,@ModelAttribute("citizen information") Citizen citizenInfo, Model model){
        citizenService.saveCitizen(citizenInfo);
        model.addAttribute("updated citizen information", citizenInfo);
        return "citizen_info";
    }

}
