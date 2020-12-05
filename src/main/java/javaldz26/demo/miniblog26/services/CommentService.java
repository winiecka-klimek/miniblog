package javaldz26.demo.miniblog26.services;

import javaldz26.demo.miniblog26.dao.CommentRepository;
import javaldz26.demo.miniblog26.dtos.CommentDetailsDto;
import javaldz26.demo.miniblog26.dtos.SubmittedCommentDto;
import javaldz26.demo.miniblog26.entities.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public SubmittedCommentDto submitComment(String commentContent, String nickname) {
        final Comment comment =  new Comment();
        comment.setCommentContent(commentContent);
        comment.setNickname(nickname);

        commentRepository.save(comment);

        return null;
    }

    public List<CommentDetailsDto> getAllComments() {
        return commentRepository.findAll().stream()
                .map(comment -> new CommentDetailsDto(comment.getId(), comment.getCommentContent(), comment.getNickname(), comment.getCreated()))
                .sorted(Comparator.comparing(CommentDetailsDto::getId))
                .collect(Collectors.toList());
    }
}
