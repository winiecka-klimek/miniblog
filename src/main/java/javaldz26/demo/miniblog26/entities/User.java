package javaldz26.demo.miniblog26.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
   @Id
   @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

   @Column(unique = true)
   private String email;
   private String nickname;
   private String password;
   private LocalDateTime created = LocalDateTime.now();

    public LocalDateTime getCreated() {return created;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
