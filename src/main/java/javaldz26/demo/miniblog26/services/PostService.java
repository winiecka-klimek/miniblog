package javaldz26.demo.miniblog26.services;

import javaldz26.demo.miniblog26.dao.PostRepository;
import javaldz26.demo.miniblog26.dao.CommentRepository;
import javaldz26.demo.miniblog26.dtos.*;
import javaldz26.demo.miniblog26.entities.Comment;
import javaldz26.demo.miniblog26.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    PostRepository postRepository;


    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public SubmittedPostDto submittedPost(String title, String postContent) {
        final Post post = new Post();
        post.setTitle(title);
        post.setPostContent(postContent);

        postRepository.save(post);
        return null;
    }

   public List<PostShortInfoDto> getAllPosts() {
       return postRepository.findAll().stream()
               .map(post -> new PostShortInfoDto(post.getTitle(),post.getCreated(), post.getPostContent()))
               .sorted(Comparator.comparing(PostShortInfoDto::getCreated).reversed())
               .collect(Collectors.toList());
   }

    public PostDetailsDto getPostDetails(Long postId) {
        return postRepository.findById(postId)
                .map(post -> new PostDetailsDto(post.getId(), post.getTitle(), post.getPostContent(), post.getCreated()))
                .orElseThrow(() -> new IllegalArgumentException("Post doesn't exist for given id: " + postId));
    }
}
