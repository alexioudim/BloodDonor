package dit.hua.team50.BloodDonor.controller;

import dit.hua.team50.BloodDonor.entity.Application;
import dit.hua.team50.BloodDonor.entity.Citizen;
import dit.hua.team50.BloodDonor.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/applications")
public class ApplicationController {


    @Autowired
    private ApplicationService applicationService;

    @GetMapping("")
    public String showApplications(Model model){
        List<Application> applications = applicationService.getApplications();
        model.addAttribute("applications", applications);

        return "applications";
    }

    @GetMapping("/{application_id}")
    public String showApplicant(@PathVariable Integer application_id, Model model){
        Citizen applicant = applicationService.findApplicant(application_id);
        model.addAttribute("applicant", applicant);

        return "applicant";
    }

    @PostMapping("/{application_id}/approve")
    public ResponseEntity<String> approveApplication(@PathVariable Integer application_id) {
        Application application = applicationService.findById(application_id);
        application.setApproval(true);
        applicationService.saveApplication(application);

        return new ResponseEntity<>("Application approved successfully", HttpStatus.OK);
    }

    @PostMapping("/{application_id}/reject")
    public ResponseEntity<String> rejectApplication(@PathVariable Integer application_id) {
        Application application = applicationService.findById(application_id);
        application.setApproval(false);
        applicationService.saveApplication(application);

        return new ResponseEntity<>("Application rejected successfully", HttpStatus.OK);
    }
}
