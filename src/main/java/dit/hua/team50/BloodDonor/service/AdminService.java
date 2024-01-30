package dit.hua.team50.BloodDonor.service;

import dit.hua.team50.BloodDonor.entity.User;
import dit.hua.team50.BloodDonor.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;
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
        userRepository.deleteById(user_id);
    }
}
