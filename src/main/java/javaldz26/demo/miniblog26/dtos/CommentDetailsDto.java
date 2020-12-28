package javaldz26.demo.miniblog26.dtos;

import java.time.LocalDateTime;

public class CommentDetailsDto {
//    Long id;
    String commentContent;
    String nickname;
    LocalDateTime created;

    public CommentDetailsDto(String commentContent, String nickname, LocalDateTime created) {
//        this.id = id;
        this.commentContent = commentContent;
        this.nickname = nickname;
        this.created = created;
    }

//    public Long getId() {
//        return id;
//    }

    public String getCommentContent() {
        return commentContent;
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDateTime getCreated() {
        return created;
    }
}
