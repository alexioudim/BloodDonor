package dit.hua.team50.BloodDonor.controller;

import dit.hua.team50.BloodDonor.entity.Application;
import dit.hua.team50.BloodDonor.entity.Citizen;
import dit.hua.team50.BloodDonor.repository.ApplicationRepository;
import dit.hua.team50.BloodDonor.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secretary")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("")
    public List<Application> showApplications() {
        return applicationService.getApplications();
    }

    @GetMapping("/{application_id}")
    public Citizen showApplicant(@PathVariable Integer application_id) {
        return applicationService.findApplicant(application_id);
    }

    @PostMapping("/{application_id}/approve")
    public ResponseEntity<String> approveApplication(@PathVariable Integer application_id) {

        applicationService.approveApplication(application_id);
        return new ResponseEntity<>("Application approved successfully", HttpStatus.OK);
    }

    @PostMapping("/{application_id}/reject")
    public ResponseEntity<String> rejectApplication(@PathVariable Integer application_id) {

        applicationService.rejectApplication(application_id);
        return new ResponseEntity<>("Application rejected successfully", HttpStatus.OK);
    }
}
