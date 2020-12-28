package javaldz26.demo.miniblog26.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class PostDetailsDto {
    private Long id;
    private String title;
    private String postContent;
    private LocalDateTime created;
    private List<CommentDetailsDto> comments;

    public PostDetailsDto(Long id, String title, String postContent, LocalDateTime created) {
        this.id = id;
        this.title = title;
        this.postContent = postContent;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPostContent() {
        return postContent;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public List<CommentDetailsDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDetailsDto> comments) {
        this.comments = comments;
    }
}
