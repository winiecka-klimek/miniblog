package javaldz26.demo.miniblog26.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 1000)
    private String commentContent;
    private String nickname;
    private LocalDateTime created = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;


    public Long getId() {
        return id;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
