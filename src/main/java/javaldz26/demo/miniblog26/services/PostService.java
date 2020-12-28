package javaldz26.demo.miniblog26.services;

import javaldz26.demo.miniblog26.dao.PostCommentRepository;
import javaldz26.demo.miniblog26.dao.PostRepository;
//import javaldz26.demo.miniblog26.dao.CommentRepository;
import javaldz26.demo.miniblog26.dtos.*;
import javaldz26.demo.miniblog26.dtos.request.NewPostForm;
//import javaldz26.demo.miniblog26.entities.Comment;
import javaldz26.demo.miniblog26.entities.Post;
import javaldz26.demo.miniblog26.entities.PostComment;
import javaldz26.demo.miniblog26.exceptions.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    PostRepository postRepository;
    PostCommentRepository postCommentRepository;


    @Autowired
    public PostService(PostRepository postRepository, PostCommentRepository postCommentRepository) {
        this.postRepository = postRepository;
        this.postCommentRepository = postCommentRepository;
    }

    public void saveNewPost(NewPostForm newPostForm) {
        final Post post = new Post();
        post.setTitle(newPostForm.getTitle());
        post.setPostContent(newPostForm.getPostContent());

        postRepository.save(post);
    }

   public List<PostShortInfoDto> getAllPostsSortedByNewest() {
       return postRepository.findAll(Sort.by("created").descending()).stream()
               .map(post -> new PostShortInfoDto(post.getId(), post.getTitle(),post.getCreated(), post.getPostContent()))
//               .sorted(Comparator.comparing(PostShortInfoDto::getCreated).reversed())
               .collect(Collectors.toList());
   }

//    public PostDetailsDto getPostDetails(Long postId) {
//        return postRepository.findById(postId)
//                .map(post -> new PostDetailsDto(post.getTitle(), post.getPostContent(), post.getCreated()))
//                .orElseThrow(() -> new IllegalArgumentException("Post doesn't exist for given id: " + postId));
//    }

    public Optional<PostDetailsDto> getPostDetails(Long postId) {
        return postRepository.findById(postId)
                .map(post -> new PostDetailsDto(
                        post.getId(),
                        post.getTitle(),
                        post.getPostContent(),
                        post.getCreated()));
    }

    public Optional<PostDetailsDto> getSinglePostInfoWithComments(Long postId) {
        final Optional<PostDetailsDto> postInfoOptional = postRepository.findById(postId)
                .map(post -> new PostDetailsDto(post.getId(),
                        post.getTitle(),
                        post.getPostContent(),
                        post.getCreated()));

        postInfoOptional.ifPresent(postDetailsDto -> {
            final List<CommentDetailsDto> commentsDtos = postCommentRepository
                    .findByPostId(postId, Sort.by("added").descending())
                    .stream()
                    .map(postComment -> new CommentDetailsDto(postComment.getCommentContent(),
                            postComment.getCommentatorNickname(),
                            postComment.getCreated()))
                    .collect(Collectors.toList());

            postDetailsDto.setComments(commentsDtos);
        });
        return postInfoOptional;
    }

    public void submitComment(Long postId, String commentContent, String commentatorNickname) {
       final Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));

        final PostComment postComment = new PostComment();
        postComment.setCommentContent(commentContent);
        postComment.setCommentatorNickname(commentatorNickname);
        postComment.setPost(post);

        postCommentRepository.save(postComment);
    }

//    public List<CommentDetailsDto> getAllCommentsByPostId(Long postId) {
////        findById(postRepository.findById(postId)).
//
//        return postCommentRepository.findAll(Sort.by("created").descending()).stream()
////                .filter( -> )
//                .map(postComment -> new CommentDetailsDto(postComment.getId(),
//                        postComment.getCommentContent(),
//                        postComment.getCommentatorNickname(),
//                        postComment.getCreated()))
//                .collect(Collectors.toList());
//    }
}
