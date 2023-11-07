package project.stylemate.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import project.stylemate.dto.style.StyleSearchCondition;
import project.stylemate.entity.Style;
import project.stylemate.enums.Gender;

import java.util.List;

import static org.springframework.util.StringUtils.*;
import static project.stylemate.entity.QStyle.*;

@RequiredArgsConstructor
public class StyleRepositoryImpl implements StyleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Style> getAllStyles(StyleSearchCondition condition, Pageable pageable) {
        //TODO: 스타일 랭킹순 정렬 작업
        List<Style> content = queryFactory
                .selectFrom(style)
                .where(
                        genderEq(condition.getGender()),
                        styleCategoryEq(condition.getStyleCategory()),
                        minHeightGoe(condition.getMinHeight()),
                        maxHeightLoe(condition.getMaxHeight())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(style.count())
                .from(style)
                .where(
                        genderEq(condition.getGender()),
                        styleCategoryEq(condition.getStyleCategory()),
                        minHeightGoe(condition.getMinHeight()),
                        maxHeightLoe(condition.getMaxHeight())
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, total);

    }

    private BooleanExpression genderEq(Gender gender) {
        return gender != null ? style.gender.eq(gender) : null;
    }

    private BooleanExpression styleCategoryEq(String styleCategory) {
        return hasText(styleCategory) ? style.styleCategory.eq(styleCategory) : null;
    }

    private BooleanExpression minHeightGoe(Integer minHeight) {
        return minHeight != null ? style.minHeight.goe(minHeight) : null;
    }

    private BooleanExpression maxHeightLoe(Integer maxHeight) {
        return maxHeight != null ? style.maxHeight.loe(maxHeight) : null;
    }
}
