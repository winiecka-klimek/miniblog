package javaldz26.demo.miniblog26.controllers;

import javaldz26.demo.miniblog26.dtos.*;
import javaldz26.demo.miniblog26.services.CommentService;
import javaldz26.demo.miniblog26.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @Autowired
    public PostController(PostService postService, CommentService commentService) {

        this.postService = postService;
        this.commentService = commentService;
    }


    @GetMapping("/posts/add")
    public String showNewPostForm() {

        return "addNewPost";
    }

    @PostMapping("/posts/add")
    public String submitNewPostForm(@RequestParam String title,
                                  @RequestParam String postContent) {

        SubmittedPostDto submittedPost = postService.submittedPost(title, postContent);

        return "postSuccesfullyAddedPage";
    }

    @GetMapping("/posts")
    public String showAllPostsPage(Model model) {
        List<PostShortInfoDto> posts = postService.getAllPosts();
        model.addAttribute("posts",  posts);
        return "postsList";
    }

    @GetMapping("/posts/{postId}")
    public String showPostDetailsPage(@PathVariable Long postId, Model model) {

        PostDetailsDto postDetails = postService.getPostDetails(postId);
        model.addAttribute("postDetails", postDetails);

        return "postDetails";
    }

    @GetMapping("/comments")
    public String showAllCommentsPage(Model model) {
        List<CommentDetailsDto> comments = commentService.getAllComments();
        model.addAttribute("comments",  comments);
        return "postsDetails";
    }

    @PostMapping("/posts/{postId}/comment/add")
    public String showAddCommentForm(@PathVariable Long postId,
                                     @RequestParam String commentContent,
                                     @RequestParam String nickname,
                                     Model model) {

        SubmittedCommentDto submittedComment = commentService.submitComment(commentContent, nickname);
        return "postDetails";
    }
}
