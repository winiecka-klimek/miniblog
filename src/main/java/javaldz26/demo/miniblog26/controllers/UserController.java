package javaldz26.demo.miniblog26.controllers;

import javaldz26.demo.miniblog26.dtos.RegisteredUserDto;
import javaldz26.demo.miniblog26.dtos.UserDetailsDto;
import javaldz26.demo.miniblog26.dtos.UserShortInfoDto;
import javaldz26.demo.miniblog26.services.LoginService;
import javaldz26.demo.miniblog26.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    private final LoginService loginService;
    private final UserService userService;

    public UserController(UserService userService, LoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }

    @GetMapping("/register")
    public String showRegisterForm() {

        return "registerForm";
    }

    @PostMapping("/register")
    public String submitRegisterForm(@RequestParam String email,
                                     @RequestParam String nickname,
                                     @RequestParam String password) {

        RegisteredUserDto registeredUserDto = userService.registeredUser(email, nickname, password);
        return "registeredUserThankYouPage";
    }

    @GetMapping("/users")
    public String showUsersPage(Model model) {
      List<UserShortInfoDto> users = userService.getAllUsers();
      model.addAttribute("users",  users);
        return "usersList";
    }

    @GetMapping("/users-by-email")
    public String showUsersByEmailPage(@RequestParam String email,
                                       Model model) {
        List<UserShortInfoDto> users = userService.findByEmailContaining(email);
        model.addAttribute("users",  users);
        return "usersList";
    }

    @GetMapping("/users/{userId}")
    public String showUserDetailsPage(@PathVariable Long userId, Model model) {
//
        UserDetailsDto userDetails = userService.getUserDetails(userId);
        model.addAttribute("userDetails", userDetails);

        return "userDetails";
    }

}
