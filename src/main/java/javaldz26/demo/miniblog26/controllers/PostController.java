package javaldz26.demo.miniblog26.controllers;

import javaldz26.demo.miniblog26.dtos.*;
import javaldz26.demo.miniblog26.dtos.request.NewPostForm;
//import javaldz26.demo.miniblog26.services.CommentService;
import javaldz26.demo.miniblog26.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class PostController {

    private final PostService postService;
//    private final CommentService commentService;

    @Autowired
    public PostController(PostService postService) {

        this.postService = postService;
//        this.commentService = commentService;
    }


    @GetMapping("/posts/add")
    public String showNewPostForm(Model model) {
        model.addAttribute("newPostForm", new NewPostForm());
        return "posts/newPostForm";
    }

//    @PostMapping("/posts/add")
//    public String submitNewPostForm(@RequestParam String title,
//                                    @RequestParam String postContent,
//                                    Model model) {
//
//        SubmittedPostDto submittedPost = postService.submittedPost(title, postContent);
//        log.info("New POST: {}, {}", title, postContent);
//
//        return "posts/newPostForm";
//    }

    @PostMapping("/posts/add")
    public String submitNewPostForm(@ModelAttribute @Valid NewPostForm newPostForm,
                                    BindingResult bindingResult) {


        log.info("New POST: {}", newPostForm);
        log.info("New POST ERRORS: {}", bindingResult.getAllErrors());
//        if(newPostForm.getTitle() == null || newPostForm.getPostContent().isBlank()
//                || newPostForm.getTitle().length() < 5 ) {
//            throw new IllegalArgumentException("Invalid user  input for  title");
//        }
        if(bindingResult.hasErrors()) {
            return "posts/newPostForm";
        }

        postService.saveNewPost(newPostForm);
        return "redirect:/";
    }

    @GetMapping("/posts")
    public String showAllPostsPage(Model model) {
        List<PostShortInfoDto> posts = postService.getAllPostsSortedByNewest();
        model.addAttribute("posts",  posts);
        return "posts/postsList";
    }

    @GetMapping("/posts/{postId}")
    public String showPostDetailsPage(@PathVariable Long postId, Model model) {

        final Optional<PostDetailsDto> postDetailsDtoOptional = postService.getPostDetails(postId);
        if(postDetailsDtoOptional.isEmpty())  {
            return "posts/postNotFoundView";
        }
//        PostDetailsDto postDetails = postService.getPostDetails(postId);
        model.addAttribute("postDetails", postDetailsDtoOptional.get());

//        List<CommentDetailsDto> comments = postService.getAllComments();
//        model.addAttribute("comments",  comments);

        return "posts/singlePostView";
    }



    @PostMapping("/posts/{postId}/comment/add")
    public String showAddCommentForm(@PathVariable Long postId,
                                     @RequestParam String commentContent,
                                     @RequestParam String commentatorNickname) {
        log.info("Comment for  post: {}, nickname: {}, text: {}", postId, commentatorNickname, commentContent);


        postService.submitComment(postId, commentContent, commentatorNickname);

        return "redirect:/posts/" + postId;
    }
}
