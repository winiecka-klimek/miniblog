package javaldz26.demo.miniblog26.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AndRequestMatcher;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/posts/add").hasRole("POST_MANAGER")
                    .antMatchers("/posts/*/comment/add").hasRole("USER")
                    .anyRequest().permitAll()
                .and()
                    .formLogin()
                    .loginPage("/login-by-spring")
                    .loginProcessingUrl("login-by-spring-submit")
                    .usernameParameter("loginUserName")
                    .passwordParameter("hiddenPassword")
                    .failureUrl("/login-by-spring?status=error")
                    .defaultSuccessUrl("/")
                .and()
                    .logout()
//                    .logoutUrl("/logout-user-spring")
                .logoutRequestMatcher(new AndRequestMatcher())
                    .invalidateHttpSession(true)
                ;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT u.email, u.password, 1 " +
                        "FROM users u WHERE u.email = ?")
                //1 na sztywno wpisuje, że uzytkownik jest aktywny,
                // na sztywno, bo nie mam w kodzie takiego parametru
                .authoritiesByUsernameQuery("SELECT u.email,  r.role_name " +
                        "FROM users u" +
                        "JOIN users_roles ur ON u.id = ur.user_id" +
                        "JOIN roles r ON ur.roles_id = r.id" +
                        "WHERE u.email = ?")
                .passwordEncoder(passwordEncoder);
    }
}
