package javaldz26.demo.miniblog26.controllers;

import javaldz26.demo.miniblog26.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    private final LoginService loginService;

    @Autowired
    public HomePageController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/")
    public String showHomePage(Model model){
        model.addAttribute("userLogged", loginService.isLogged());
        model.addAttribute("userInfo", loginService.getUserSessionDto());
        return "homePage";
    }
}
