package dit.hua.team50.BloodDonor.service;

import dit.hua.team50.BloodDonor.entity.Role;
import dit.hua.team50.BloodDonor.entity.User;
import dit.hua.team50.BloodDonor.repository.RoleRepository;
import dit.hua.team50.BloodDonor.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public Integer saveUser(User user) {
        String passwd = user.getPassword();
        String encodedPasswd = passwordEncoder.encode(passwd);
        user.setPassword(encodedPasswd);

        Role role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        user.setRole(role);

        user = userRepository.save(user);
        return user.getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> opt = userRepository.findByUsername(username);

        if(opt.isEmpty())
            throw new UsernameNotFoundException("User with username:" + username +" not found!");
        else {
            User user = opt.get();
            Role role = user.getRole();
            return new org.springframework.security.core.userdetails.User(
                    user.getPassword(),
                    user.getUsername(),
                    Collections.singleton(new SimpleGrantedAuthority(role.getName()))
            );
        }
    }
}
