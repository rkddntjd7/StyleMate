package project.stylemate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.stylemate.entity.Style;

public interface StyleRepository extends JpaRepository<Style, Long> {
}
