package javaldz26.demo.miniblog26.hello;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class HelloDateTimeProvider {
    public LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }

}
