package javaldz26.demo.miniblog26.dtos;

import java.time.LocalDateTime;

public class UserShortInfoDto {
    private Long id;
    private String email;
    private String nickname;

    public UserShortInfoDto(Long id, String email, String nickname) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }
}
