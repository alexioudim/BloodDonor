package dit.hua.team50.BloodDonor.service;

import dit.hua.team50.BloodDonor.entity.Application;
import dit.hua.team50.BloodDonor.entity.Citizen;
import dit.hua.team50.BloodDonor.payload.request.ApplicationRequest;
import dit.hua.team50.BloodDonor.repository.ApplicationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.time.LocalDate.now;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Transactional
    public List<Application> getApplications() {

        return applicationRepository.findAll();
    }

    @Transactional
    public Citizen findApplicant(Integer application_id) {
        Application application = applicationRepository.findById(application_id)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));;
        return application.getCitizen();
    }

    @Transactional
    public Application findById(Integer application_id){
        return applicationRepository.findById(application_id).get();
    }

    @Transactional
    public Application saveApplication(Application application){
        return applicationRepository.save(application);
    }

    @Transactional
    public void approveApplication(Integer application_id) {
        Application application = applicationRepository.findById(application_id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        application.setApproval_status("Approved");
        applicationRepository.save(application);
    }

    @Transactional
    public Application rejectApplication(Integer application_id) {
        Application application = applicationRepository.findById(application_id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        application.setApproval_status("Rejected");
        return applicationRepository.save(application);
    }

    @Transactional
    public Application addApplication(Citizen citizen, ApplicationRequest applicationRequest) {
        Application application = new Application();
        application.setCitizen(citizen);
        application.setDate_created(now());
        application.setApproval_status("Pending");
        application.setId(25);
        application.setRecent_blood_tests(applicationRequest.getRecent_blood_tests());
        return applicationRepository.save(application);
    }
}
