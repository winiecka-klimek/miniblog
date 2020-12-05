package javaldz26.demo.miniblog26.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HelloController {

    private final TemperatureProvider temperatureProviderService;
    private final SayingsService sayingsService;
    private final StringStatisticsService stringStatisticsService;

    @Autowired
    public HelloController(TemperatureProvider temperatureProviderService,
                           SayingsService sayingsService,
                           StringStatisticsService stringStatisticsService) {
        this.temperatureProviderService = temperatureProviderService;
        this.sayingsService = sayingsService;
        this.stringStatisticsService = stringStatisticsService;
    }





    @GetMapping("/hello")
    public String helloPage(){
        return "hello/helloView"; //przez to, że jest to  Controller, nie restcontroller, zwroci nam widok
    } //helloView-postfix.html

    @GetMapping("/hello-user")
    public String helloUserPage(@RequestParam String name, Model model) {
        model.addAttribute("username", name);
        System.out.println(name);
        return "hello/helloUserView";
    }

//    @GetMapping("/hello-user")
//    public String helloUserPage(@RequestParam(name = "name") String nameParam,
//                                Model model) {
//        //Map.put("username", name)
//        model.addAttribute("username", nameParam);
////        System.out.println(name);
//        return "helloDynamicUserView";
//    }

//
//    @GetMapping("/hello-{name}")
//    public String helloUserPageByPath(@PathVariable String name,
//                                      Model model) {
//        //Map.put("username", name)
//        model.addAttribute("username", name);
////        System.out.println(name);
//        return "helloDynamicUserView";
//    }
    @GetMapping("/hello-user-form")
    public String helloUserPageForm(Model model) {

        final HelloUser user = new HelloUser();
        model.addAttribute("user", user);
    return "hello/helloUserFormView";
    }

    @GetMapping("hello-user-form-submit")
    public String helloUserSubmitForm(@RequestParam String firstname,
                                      @RequestParam String lastname,
                                      Model model) {
        final HelloUser helloUser = new HelloUser(firstname,  lastname);
        model.addAttribute("user", helloUser);
        return "hello/helloUserSubmitFormView";
    }

    @GetMapping("hello-user-form-submit-2")
    public String helloUserSubmitForm2(@RequestParam String firstname,
                                      @RequestParam String lastname,
                                      Model model) {
        final HelloUser helloUser = new HelloUser(firstname,  lastname);
        model.addAttribute("user", helloUser);
        return "hello/helloUserSubmitFormView";
    }

    @GetMapping("/info")
    public String showCurrentTemperaturePage(Model model) {

        int currentTemperature = temperatureProviderService.currentTemperature();
        model.addAttribute("temperature", currentTemperature);
        model.addAttribute("randomSaying",sayingsService.getRandomSaying());
        return "hello/informationView";
    }

    @GetMapping("/hello-user-object")
    public String helloUserPageWithObject(@RequestParam String firstname,
                                          @RequestParam(required = false) String lastname,
                                          Model model) {
//        model.addAttribute("firstname", firstname);
//        model.addAttribute("lastname", lastname);
        final HelloUser user = new HelloUser(firstname, lastname);
        model.addAttribute("user", user);

        final List<HelloCountry> helloCountries = List.of(
                new HelloCountry("Poland", 100L),
                new HelloCountry("USA", 10000L));

//        final ArrayList<HelloCountry> helloCountryArrayList = new ArrayList<>();
//        helloCountryArrayList.add(new HelloCountry("Poland", 100L));
//        helloCountryArrayList.add(new HelloCountry("USA", 10000L));

        model.addAttribute("countries", helloCountries);

        return "hello/helloUserObjectView";
    }

    @GetMapping("/hello-words")
    public String showWordsForm() {
    return "hello/helloWords";
    }

    @GetMapping("/hello-words-submit")
    public String showWordsSubmit(@RequestParam String word1,
                                  @RequestParam String word2,
                                  Model model) {
        stringStatisticsService.sumAllCharacters(word1, word2);

//       boolean lengthIsEven = stringStatisticsService.isLengthEven(word1, word2);
//
//       Map<String, Integer> charactersOccurence = stringStatisticsService.CountCharactersOccurence(word1, word2);


        return "hello/helloWords";
    }
}
