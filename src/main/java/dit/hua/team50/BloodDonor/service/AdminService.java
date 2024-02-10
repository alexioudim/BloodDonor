package dit.hua.team50.BloodDonor.service;

import dit.hua.team50.BloodDonor.entity.Role;
import dit.hua.team50.BloodDonor.entity.User;
import dit.hua.team50.BloodDonor.repository.CitizenRepository;
import dit.hua.team50.BloodDonor.repository.RoleRepository;
import dit.hua.team50.BloodDonor.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Transactional
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public User findUserById(Long user_id){
        return userRepository.findById(user_id).get();
    }
    @Transactional
    public User saveUser(User user){
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long user_id) {
        User user = userRepository.findById(user_id).get();
        Set<Role> roles = user.getRoles();
        Role role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        if (roles.contains(role)) {
            if (citizenRepository.findByUserId(user_id).isPresent()){
                citizenRepository.delete(citizenRepository.findByUserId(user_id).get());

            }
        }
        userRepository.deleteById(user_id);
    }
}
