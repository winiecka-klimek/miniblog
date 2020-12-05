package javaldz26.demo.miniblog26.dtos;

import java.time.LocalDateTime;

public class UserDetailsDto {
    private Long id;
    private String email;
    private String nickname;
    private LocalDateTime created;

    public UserDetailsDto(Long id, String email, String nickname, LocalDateTime created) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.created = created;
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

    public LocalDateTime getCreated() {
        return created;
    }
}
