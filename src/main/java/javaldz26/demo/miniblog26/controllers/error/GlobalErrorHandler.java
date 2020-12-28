package javaldz26.demo.miniblog26.controllers.error;

import javaldz26.demo.miniblog26.exceptions.UserDoesntExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalErrorHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserDoesntExistException.class)
    public String handle(UserDoesntExistException e) {
        log.warn("Global exception handling for: {}",  e.getMessage());

        return "posts/noPostsFound";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handle(IllegalArgumentException e) {
        log.warn("Global exception handling for: {}",  e.getMessage());

        return "posts/noPostsFound";
    }

    @ExceptionHandler(Exception.class)
    public String handle(Exception e) {
        log.warn("Unknown exception: {}",  e.getMessage());

        return "/";
    }
}
