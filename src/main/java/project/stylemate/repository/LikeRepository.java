package project.stylemate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.stylemate.entity.Like;
import project.stylemate.entity.Member;
import project.stylemate.entity.Style;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByMemberAndStyle(Member member, Style style);

    Long countByStyleId(Long styleId);
}
