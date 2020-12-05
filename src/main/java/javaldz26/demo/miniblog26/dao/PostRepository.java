package javaldz26.demo.miniblog26.dao;

import javaldz26.demo.miniblog26.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
