package dit.hua.team50.BloodDonor.controller;

import dit.hua.team50.BloodDonor.entity.Application;
import dit.hua.team50.BloodDonor.entity.Citizen;
import dit.hua.team50.BloodDonor.service.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/citizen/{citizen_id}")
public class CitizenController {

    @Autowired
    private CitizenService citizenService;

    @GetMapping("/my_applications")
    public String myApplications(@PathVariable Integer citizen_id, Model model) {

        List<Application> myApplications = citizenService.getMyApplications(citizen_id);
        model.addAttribute("myApplications", myApplications);

        return "citizen";
    }

    @GetMapping("my_applications/new")
    public String newApplication(@PathVariable Integer citizen_id, Model model){

        Application application = new Application();
        model.addAttribute("newApplication", application );

        return "add_application";
    }

    @GetMapping("/profile")
    public String citizenInformation(@PathVariable Integer citizen_id, Model model){
        Citizen information = citizenService.getCitizen(citizen_id);
        model.addAttribute("information", information);
        return "citizen_info";
    }

    @GetMapping("/profile/edit")
    public String editCitizenInformation(@PathVariable Integer citizen_id){

        return "edit_citizen_info";
    }

}
