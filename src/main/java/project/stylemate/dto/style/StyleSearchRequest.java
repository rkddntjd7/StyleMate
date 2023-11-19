package project.stylemate.dto.style;

import lombok.Getter;
import project.stylemate.entity.Like;
import project.stylemate.enums.Gender;

@Getter
public class StyleSearchRequest {

    private Gender gender;
    private Integer minHeight;
    private Integer maxHeight;
    private String styleCategory;

    public StyleSearchCondition toCond() {
        return StyleSearchCondition.builder()
                .gender(gender)
                .minHeight(minHeight)
                .maxHeight(maxHeight)
                .styleCategory(styleCategory)
                .build();
    }
}
