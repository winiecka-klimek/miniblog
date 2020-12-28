package javaldz26.demo.miniblog26.controllers;

import javaldz26.demo.miniblog26.services.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "loginForm";
    }

    @PostMapping("/login-submit-data")
    public String submitLoginForm(@RequestParam String username,
                                  @RequestParam String password) {
        try {
            loginService.loginUser(username, password);
        } catch (Exception e) {
            log.warn("Couldn't log user in: {}", e.getMessage(), e);
            return "redirect:/login";
        }

        return "redirect:/";
    }

    @GetMapping("/logout-user")
    public String logoutUser() {
        loginService.logout();
        return "/redirect:/";
    }

    @GetMapping("/login-by-spring")
    public String showLoginFormBySpring() {
        return "loginForm";
    }

    @PostMapping("/login-by-spring-submit")
    public String submitLoginFormBySpring(@RequestParam String username,
                                          @RequestParam String password) {
        try {
            loginService.loginUser(username, password);
        } catch (Exception e) {
            log.warn("Couldn't log user in: {}", e.getMessage(), e);
            return "redirect:/login";
        }
        return "redirect:/";
    }
}
