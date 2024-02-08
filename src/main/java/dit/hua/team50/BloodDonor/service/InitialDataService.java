package dit.hua.team50.BloodDonor.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import dit.hua.team50.BloodDonor.entity.Application;
import dit.hua.team50.BloodDonor.entity.Citizen;
import dit.hua.team50.BloodDonor.entity.Role;
import dit.hua.team50.BloodDonor.entity.User;
import dit.hua.team50.BloodDonor.repository.ApplicationRepository;
import dit.hua.team50.BloodDonor.repository.CitizenRepository;
import dit.hua.team50.BloodDonor.repository.RoleRepository;
import dit.hua.team50.BloodDonor.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDate.now;

@Service
public class InitialDataService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    public void roleSetup() {
        roleRepository.findByName("ROLE_ADMIN").orElseGet(() -> {
            roleRepository.save(new Role("ROLE_ADMIN"));
            return null;
        });
        roleRepository.findByName("ROLE_USER").orElseGet(() -> {
            roleRepository.save(new Role("ROLE_USER"));
            return null;
        });

        roleRepository.findByName("ROLE_SECRETARY").orElseGet(() -> {
            roleRepository.save(new Role("ROLE_SECRETARY"));
            return null;
        });
    }


   public void userSetup() {

       userRepository.findByUsername("admin").orElseGet(() -> {
           User admin = new User();
           admin.setUsername("admin");
           admin.setEmail("MasterControlProgram@gmail.com");
           admin.setPassword(encoder.encode("adminpass"));

           userRepository.save(admin);

           return null;
       });


        userRepository.findByUsername("Alikh").orElseGet(() -> {

            User secretary = new User();
            secretary.setUsername("Alikh");
            secretary.setEmail("Alikh@gmail.com");
            secretary.setPassword(encoder.encode("secretarypass"));

            userRepository.save(secretary);

            return null;
        });

    }

    @PostConstruct
    public void setup() {
        this.roleSetup();
        this.userSetup();
    }
}
