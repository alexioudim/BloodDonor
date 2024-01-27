package dit.hua.team50.BloodDonor.service;

import dit.hua.team50.BloodDonor.entity.Application;
import dit.hua.team50.BloodDonor.entity.Citizen;
import dit.hua.team50.BloodDonor.entity.User;
import dit.hua.team50.BloodDonor.repository.CitizenRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CitizenService {

    @Autowired
    private CitizenRepository citizenRepository;
    @Transactional
    public List<Application> getMyApplications(Integer citizen_id) {
        Optional<Citizen> citizen = citizenRepository.findById(citizen_id);

        if (citizen.isPresent()) {
            return citizen.get().getApplications();
        } else {
            // Not found scenario
            return Collections.emptyList();
        }
    }

    @Transactional
    public Citizen getCitizen(Integer citizen_id){
        return citizenRepository.findById(citizen_id).get();
    }

    @Transactional
    public Integer saveCitizen(Citizen citizen) {
        Citizen savedCitizen = citizenRepository.save(citizen);
        return savedCitizen.getId();
    }

    @Transactional
    public Integer updateCitizen(Citizen citizen) {
        Citizen savedCitizen = citizenRepository.save(citizen);
        return savedCitizen.getId();
    }


    @Transactional
    public Citizen getCitizenByUserId(Long user_id) {
        return citizenRepository.findByUserId(user_id).get();
    }
}
