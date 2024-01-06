package dit.hua.team50.BloodDonor.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public void authenticateUser(){

    }

    @PostMapping("/register")
    public void registerUser(){

    }
}
