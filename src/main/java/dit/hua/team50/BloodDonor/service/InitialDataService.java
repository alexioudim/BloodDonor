package dit.hua.team50.BloodDonor.service;

import dit.hua.team50.BloodDonor.entity.Role;
import dit.hua.team50.BloodDonor.entity.User;
import dit.hua.team50.BloodDonor.repository.RoleRepository;
import dit.hua.team50.BloodDonor.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class InitialDataService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

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

           Role role = roleRepository.findByName("ROLE_ADMIN")
                   .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
           Set<Role> roles = new HashSet<>();
           roles.add(role);

           admin.setRoles(roles);

           userRepository.save(admin);

           return null;
       });


        userRepository.findByUsername("Alikh").orElseGet(() -> {

            User secretary = new User();
            secretary.setUsername("Alikh");
            secretary.setEmail("Alikh@gmail.com");
            secretary.setPassword(encoder.encode("secretarypass"));

            Role role = roleRepository.findByName("ROLE_SECRETARY")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            Set<Role> roles = new HashSet<>();
            roles.add(role);

            secretary.setRoles(roles);

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
