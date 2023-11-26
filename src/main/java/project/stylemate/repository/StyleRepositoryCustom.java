package project.stylemate.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.stylemate.dto.style.StyleSearchCondition;
import project.stylemate.entity.Style;

public interface StyleRepositoryCustom {
    Page<Style> getAllStyles(StyleSearchCondition condition, Pageable pageable);
}
