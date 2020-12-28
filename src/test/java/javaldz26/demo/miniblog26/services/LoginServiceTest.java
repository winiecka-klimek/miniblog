package javaldz26.demo.miniblog26.services;

import javaldz26.demo.miniblog26.dao.UserRepository;
import javaldz26.demo.miniblog26.entities.User;
import javaldz26.demo.miniblog26.exceptions.InvalidCredentialsException;
import javaldz26.demo.miniblog26.exceptions.UserDoesntExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LoginService loginService;

    @DisplayName("Should login User with valid credentials")
    @Test
    public void should_login_user_with_login_credentials() {

        //given
        String validUsername = "valid.user@test.pl";
        String validPassword = "VALID_PASSWORD";

        final User validUser = new User();
        validUser.setEmail(validUsername);
        validUser.setPassword(validPassword);
        when(userRepository.findUserByLogin(validUsername))
                .thenReturn(Optional.of(validUser));

        //when
        loginService.loginUser(validUsername, validPassword);
        //then
        org.junit.jupiter.api.Assertions.assertEquals(true, loginService.isLogged());
        org.assertj.core.api.Assertions.assertThat(loginService.isLogged()).isTrue();
    }

    @Test
    public void should_not_login_user_with_invalid_username() {

        //given
        String validUsername = "valid.user@test.pl";
        String validPassword = "VALID_PASSWORD";
        String invalidUsername = "invalid.user@test.pl";

        final User validUser = new User();
        validUser.setEmail(validUsername);
        validUser.setPassword(validPassword);
        when(userRepository.findUserByLogin(validUsername))
                .thenReturn(Optional.of(validUser));
        when(userRepository.findUserByLogin(any()))
                .thenReturn(Optional.empty());

        //when
        loginService.loginUser(invalidUsername, validPassword);
        //then
        org.junit.jupiter.api.Assertions.assertEquals(true, loginService.isLogged());
        org.assertj.core.api.Assertions.assertThat(loginService.isLogged()).isTrue();
    }

    @Test
    public void should_not_login_invalid_password() {
        //given
        String validUsername = "valid.user@test.pl";
        String validPassword = "VALID_PASSWORD";
        String invalidPassword = "INVALID_PASSWORD";

        final User validUser = new User();
        validUser.setEmail(validUsername);
        validUser.setPassword(validPassword);

        when(userRepository.findUserByLogin(validUsername))
                .thenReturn(Optional.of(validUser));

        //when
        //JUnit5
        Assertions.assertThrows(InvalidCredentialsException.class, () -> loginService.loginUser(validUsername, invalidPassword));

        //Assert j
       org.assertj.core.api.Assertions.assertThatThrownBy( () -> loginService.loginUser(validUsername, invalidPassword))
               .isInstanceOf(InvalidCredentialsException.class)
               .hasMessageContaining("invalid");

       final Throwable catchedException = catchThrowable(() -> loginService.loginUser(validUsername, invalidPassword));
        //then
        org.assertj.core.api.Assertions.assertThat(loginService.isLogged()).isFalse();
        org.assertj.core.api.Assertions.assertThat(catchedException)
                .isInstanceOf(InvalidCredentialsException.class)
                .hasMessageContaining("invalid");


    }


    @Test
    public void should_throw_exception_with_invalid_user() {

        //given
//        String validUsername = "valid.user@test.pl";
        String validPassword = "VALID_PASSWORD";
        String invalidUsername = "invalid.user@test.pl";

//        final User validUser = new User();
//        validUser.setEmail(validUsername);
//        validUser.setPassword(validPassword);

        when(userRepository.findUserByLogin(any()))
                .thenReturn(Optional.empty());

        //when
        try {
            loginService.loginUser(invalidUsername, validPassword);
            fail("Login service should throw exception");
        } catch ( UserDoesntExistException e) {
            //test ok...
        }

        //then
//        org.assertj.core.api.Assertions.assertThat(loginService.loginUser(invalidUsername, validPassword)).isFalse();
        }
}
