package javaldz26.demo.miniblog26.exceptions;

public class PostNotFoundException extends RuntimeException{

    private Long postId;

    public PostNotFoundException(Long postId) {
        this.postId = postId;
    }

    @Override
    public String getMessage() {
        return String.format("Post not found: %d", postId);
    }

    public Long getPostId() {
        return postId;
    }

    
}
