package javaldz26.demo.miniblog26.dtos;

import java.time.LocalDateTime;

public class PostDetailsDto {
    private Long id;
    private String title;
    private String postContent;
    private LocalDateTime created;

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
}
