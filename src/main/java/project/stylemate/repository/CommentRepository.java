package project.stylemate.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.stylemate.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
