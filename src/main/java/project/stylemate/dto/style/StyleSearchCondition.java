package project.stylemate.dto.style;

import lombok.Builder;
import lombok.Getter;
import project.stylemate.entity.Style;
import project.stylemate.enums.Gender;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class StyleSearchCondition {

    private Gender gender;
    private Integer minHeight;
    private Integer maxHeight;
    private String styleCategory;

    public Style toEntity() {
        return Style.builder()
                .gender(gender)
                .minHeight(minHeight)
                .maxHeight(maxHeight)
                .styleCategory(styleCategory)
                .build();
    }

}
