package javaldz26.demo.miniblog26.hello;

import lombok.Getter;
import lombok.Setter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Getter
@Setter
public class HelloUser {
    private String firstname;
    private String lastname;

    public HelloUser() {
    }

    public HelloUser(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
//    @GetMapping("/hello-user-object")
//    public String helloUserPageWithObject(@RequestParam String firstname,
//                                          @RequestParam(required = false) String lastname,
//                                          Model model) {
////        model.addAttribute("firstname", firstname);
////        model.addAttribute("lastname", lastname);
//
//        final HelloUser user = new HelloUser(firstname, lastname);
//        model.addAttribute("user", user);
//
//       final List<HelloCountry> helloCountries = List.of(
//                new HelloCountry("Poland", 100L),
//        new HelloCountry("USA", 10000L)
//        );
//
//       model.addAttribute();
//
//        return "helloUserObjectView";
//    }

    @Override
    public String toString() {
        return "HelloUser{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }



}
