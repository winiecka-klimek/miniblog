package javaldz26.demo.miniblog26.dtos;

import javaldz26.demo.miniblog26.dao.PostCommentRepository;
import javaldz26.demo.miniblog26.dao.PostRepository;
import javaldz26.demo.miniblog26.entities.Post;
import javaldz26.demo.miniblog26.entities.PostComment;
import javaldz26.demo.miniblog26.services.PostService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class PostDetailsDtoTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private PostCommentRepository postCommentRepository;

    @Test
    public void should_return_post_dto_with_comments_for_existing_post() {

        //given
        Long postId = 10L;
        final Post post = new Post();
        post.setId(postId);
        Mockito.when(postRepository.findById(postId))
                .thenReturn(Optional.of(post));
        Mockito.when(postCommentRepository.findByPostId(eq(postId), any()))
                .thenReturn(List.of(
                        new PostComment(),
                        new PostComment()
                ));

        //when
        final Optional<PostDetailsDto> postInfoWithComments = postService.getSinglePostInfoWithComments(postId);

        //then

        Assertions.assertThat(postInfoWithComments).isNotEmpty();
        Assertions.assertThat(postInfoWithComments.get())
                .hasFieldOrPropertyWithValue("id", postId);
        Assertions.assertThat(postInfoWithComments.get().getComments())
                .hasSize(2);

    }

}
