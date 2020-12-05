package javaldz26.demo.miniblog26.dao;

import javaldz26.demo.miniblog26.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
