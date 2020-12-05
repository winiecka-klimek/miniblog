package javaldz26.demo.miniblog26.services;

import javaldz26.demo.miniblog26.dao.UserRepository;
import javaldz26.demo.miniblog26.dtos.UserSessionDto;
import javaldz26.demo.miniblog26.entities.User;
import javaldz26.demo.miniblog26.exceptions.InvalidCredentialsException;
import javaldz26.demo.miniblog26.exceptions.UserDoesntExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Slf4j
//@Scope("prototype")
@SessionScope
@Service
public class LoginService {

    private final UserRepository userRepository;

    private UserSessionDto userSessionDto;

    private  UUID uuid;

    private boolean logged = false;

    public boolean isLogged() {
        return logged;
    }

    public UserSessionDto getUserSessionDto() {
        return userSessionDto;
    }

    @Autowired
    public LoginService(UserRepository userRepository) {
        uuid = UUID.randomUUID();
        log.info("LoginService creating instance: " + uuid.toString());
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        log.info("LoginService @PostConstruct: {}", uuid.toString());
    }


//    public void loginUser(String username, String password) {
//        log.info("LoginService login user: {} / {}" , username, uuid.toString());
//    }

    @PreDestroy
    public void destroy() {
        log.info("LoginService @PreDestroy: {}", uuid.toString());
    }

    public void loginUser(String username, String password) {
//        final List<User> foundUsers = userRepository.findByEmail(username)
//
//                if(foundUsers.isEmpty()) {
//                    throw new UserDoesntExistException(username);
//                }
////                if(foundUsers.size() ...) {

        final User user = userRepository.findUserByLogin(username)
                .orElseThrow(() -> new UserDoesntExistException(username));

        if (!user.getPassword().equals(password)) {
            throw new InvalidCredentialsException();
        }

        this.userSessionDto = new UserSessionDto(user.getEmail(), user.getNickname());

        this.logged = true;
    }


    public void logout() {
        this.logged = false;
        this.userSessionDto = null;
    }

}

