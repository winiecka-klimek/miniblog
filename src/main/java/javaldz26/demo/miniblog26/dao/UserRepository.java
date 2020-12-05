package javaldz26.demo.miniblog26.dao;

import javaldz26.demo.miniblog26.entities.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findUserByLogin(String email);

    List<User> findByEmailContaining(String emailLike);

    List<User> findByEmailContaining(String emailLike, Sort sort);

    List<User> findByEmailAndNickname(String email, String nickname);
}
