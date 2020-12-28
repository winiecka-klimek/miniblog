package javaldz26.demo.miniblog26.dao;

import javaldz26.demo.miniblog26.entities.PostComment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
    List<PostComment> findByPostId(Long id, Sort sort);
}
