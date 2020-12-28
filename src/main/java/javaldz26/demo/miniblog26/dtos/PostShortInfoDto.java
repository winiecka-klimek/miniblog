package javaldz26.demo.miniblog26.dtos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PostShortInfoDto {
    private Long id;
    private String title;
    private String postAbstract;
    private LocalDateTime created;

    public PostShortInfoDto(Long id, String title, LocalDateTime created, String postAbstract ) {
        this.id = id;
        this.title = title;
        this.created = created;
        this.postAbstract = postAbstract;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

//    public String getPostContent() {
//        return postContent.substring(0, 51);
//    }

    public LocalDateTime getCreated() {
        return created;
    }

    public String getPostAbstract() {
        return postAbstract;
    }
}
