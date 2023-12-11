package project.stylemate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.stylemate.entity.BookMark;
import project.stylemate.entity.Member;
import project.stylemate.entity.Style;

import java.util.Optional;

public interface BookMarkRepository extends JpaRepository<BookMark, Long> {

    Optional<BookMark> findByMemberAndStyle(Member member, Style style);
}
