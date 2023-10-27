package project.stylemate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.stylemate.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
