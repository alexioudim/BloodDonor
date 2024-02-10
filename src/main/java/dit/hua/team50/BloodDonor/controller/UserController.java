package dit.hua.team50.BloodDonor.controller;

import dit.hua.team50.BloodDonor.entity.Application;
import dit.hua.team50.BloodDonor.entity.Citizen;
import dit.hua.team50.BloodDonor.entity.Role;
import dit.hua.team50.BloodDonor.entity.User;
import dit.hua.team50.BloodDonor.payload.request.EditUserRequest;
import dit.hua.team50.BloodDonor.repository.ApplicationRepository;
import dit.hua.team50.BloodDonor.repository.RoleRepository;
import dit.hua.team50.BloodDonor.repository.UserRepository;

import dit.hua.team50.BloodDonor.service.AdminService;
import dit.hua.team50.BloodDonor.service.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminService adminService;

    @Autowired
    private CitizenService citizenService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder encoder;


    @GetMapping("/users")
    public List<User> showUsers() {return adminService.getUsers();
    }

    @PostMapping("/users/new")
    public User newUser(@RequestBody User userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));

        return adminService.saveUser(userInfo);
    }

    @PostMapping("/{user_id}/citizen")
    public Citizen newCitizen(@PathVariable Long user_id, @RequestBody Citizen newCitizen) {
        User user = adminService.findUserById(user_id);
        Citizen citizen = new Citizen(newCitizen.getFname(), newCitizen.getLname(), user.getEmail(), newCitizen.getPhone_number(), newCitizen.getDate_of_birth(), newCitizen.getAddress(), newCitizen.getBlood_type(), user);
        return citizenService.saveCitizen(citizen);
    }

    /*@PutMapping("/{user_id}")
    public User editUser(@PathVariable long user_id, @RequestBody EditUserRequest userInfo) {
        User user = adminService.findUserById(user_id);
        user.setUsername(userInfo.getUsername());
        user.setEmail(userInfo.getEmail());
        if (!(user.getPassword().equals(userInfo.getPassword()))) {
            user.setPassword(encoder.encode(userInfo.getPassword()));
        }

        String roleName = userInfo.getRoleName();
        Set<Role> roles = new HashSet<>();


        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(role);

        user.setRoles(roles);
        return adminService.saveUser(user);
    }*/

    @PutMapping("/{user_id}")
    public User editUser(@PathVariable long user_id, @RequestBody User userInfo) {
        User user = adminService.findUserById(user_id);
        user.setUsername(userInfo.getUsername());

        if (!(user.getPassword().equals(userInfo.getPassword()))) {
            user.setPassword(encoder.encode(userInfo.getPassword()));
        }

        user.setEmail(userInfo.getEmail());
        user.setRoles(userInfo.getRoles());

        return adminService.saveUser(userInfo);
    }

    @DeleteMapping("/{user_id}")
    public void deleteUser(@PathVariable long user_id) {
        adminService.deleteUser(user_id);
    }

}
