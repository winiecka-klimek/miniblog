package javaldz26.demo.miniblog26.dtos.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NewPostForm {

    @Size(min = 3, max = 100)
    @NotBlank(message = "Tytuł nie może byc pusty, ani zawierać samych białych znaków")
    private String title;

    @NotNull
    @Size(max = 2000)
    private String postContent;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    @Override
    public String toString() {
        return "NewPostForm{" +
                "title='" + title + '\'' +
                ", postContent='" + postContent + '\'' +
                '}';
    }
}
