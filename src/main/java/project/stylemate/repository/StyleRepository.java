package project.stylemate.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.stylemate.entity.Style;

import javax.persistence.EntityManager;

public interface StyleRepository extends JpaRepository<Style, Long>, StyleRepositoryCustom {
}
