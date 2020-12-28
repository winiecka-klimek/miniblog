package javaldz26.demo.miniblog26.controllers;

import javaldz26.demo.miniblog26.services.LoginService;
import javaldz26.demo.miniblog26.services.UserContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    private final LoginService loginService;
    private final UserContextService userContextService;

    @Autowired
    public HomePageController(LoginService loginService, UserContextService userContextService) {
        this.loginService = loginService;
        this.userContextService = userContextService;
    }

    @GetMapping("/")
    public String showHomePage(Model model){
        model.addAttribute("userLogged", loginService.isLogged());
        model.addAttribute("userInfo", loginService.getUserSessionDto());
        model.addAttribute("loddedAs", userContextService.getCurrentlyLoggedUsername());
        return "homePage";
    }
}
