package dit.hua.team50.BloodDonor.service;

import dit.hua.team50.BloodDonor.entity.Application;
import dit.hua.team50.BloodDonor.entity.Citizen;
import dit.hua.team50.BloodDonor.repository.ApplicationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Application application = applicationRepository.findById(application_id).get();
        return application.getCitizen();
    }

    @Transactional
    public Application findById(Integer application_id){
        return applicationRepository.findById(application_id).get();
    }

    @Transactional
    public void saveApplication(Application application){
        applicationRepository.save(application);
    }

}
