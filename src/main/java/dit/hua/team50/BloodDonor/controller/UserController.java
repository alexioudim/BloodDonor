package dit.hua.team50.BloodDonor.controller;

import dit.hua.team50.BloodDonor.entity.Application;
import dit.hua.team50.BloodDonor.entity.User;
import dit.hua.team50.BloodDonor.repository.ApplicationRepository;
import dit.hua.team50.BloodDonor.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationRepository applicationRepository;


    @GetMapping("/users")
    public List<User> showUsers() {
        return userRepository.findAll();
    }

    /*@PostMapping("/users/new")
    public User newUser() {

    }*/

}
