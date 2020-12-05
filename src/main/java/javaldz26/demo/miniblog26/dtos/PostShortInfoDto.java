package javaldz26.demo.miniblog26.dtos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PostShortInfoDto {

    private String title;
    private String postContent;
    private LocalDateTime created;

    public PostShortInfoDto(String title, LocalDateTime created, String postContent ) {
        this.title = title;
        this.created = created;
        this.postContent = postContent;
    }

    public String getTitle() {
        return title;
    }

    public String getPostContent() {
        return postContent.substring(0, 51);
    }

    public LocalDateTime getCreated() {
        return created;
    }
}
