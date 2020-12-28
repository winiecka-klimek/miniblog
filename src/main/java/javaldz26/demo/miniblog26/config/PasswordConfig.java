package javaldz26.demo.miniblog26.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    //to jak  metoda controllera, przepis na storzenie password encodera
    //trzeba powiedziec Springowi, że ta metoda jest przepisem na stworzenie beana
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
