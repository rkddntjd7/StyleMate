package project.stylemate.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class BeanConfig {

    @Bean
    JPAQueryFactory jpaQueryFactory(EntityManager em) {
        return new JPAQueryFactory(em);
    }
}
