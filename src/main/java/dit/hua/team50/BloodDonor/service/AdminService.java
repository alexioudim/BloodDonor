package dit.hua.team50.BloodDonor.service;

import dit.hua.team50.BloodDonor.entity.Role;
import dit.hua.team50.BloodDonor.entity.User;
import dit.hua.team50.BloodDonor.repository.CitizenRepository;
import dit.hua.team50.BloodDonor.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CitizenRepository citizenRepository;
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
        if (roles.contains("ROLE_USER")) {
            citizenRepository.delete(citizenRepository.findByUserId(user_id).get());
        }
        userRepository.deleteById(user_id);
    }
}
