package dit.hua.team50.BloodDonor.controller;

import dit.hua.team50.BloodDonor.entity.Citizen;
import dit.hua.team50.BloodDonor.entity.User;
import dit.hua.team50.BloodDonor.repository.UserRepository;
import dit.hua.team50.BloodDonor.service.CitizenService;
import dit.hua.team50.BloodDonor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CitizenService citizenService;


    @GetMapping("/register")
    public String register(Model model){
        User user = new User();
        Citizen citizen = new Citizen();
        model.addAttribute("user", user);
        model.addAttribute("citizen", citizen);
        return "user_registration";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user, @ModelAttribute Citizen citizen, Model model){
        System.out.println("Roles: "+ user.getRoles());
        Integer id = userService.saveUser(user);

        citizen.setUser(user);
        citizenService.saveCitizen(citizen);

        String message = "User '"+id+"' saved successfully !";
        model.addAttribute("msg", message);
        return "home";
    }

    /*@GetMapping("/users")
    public String showUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }*/

}
