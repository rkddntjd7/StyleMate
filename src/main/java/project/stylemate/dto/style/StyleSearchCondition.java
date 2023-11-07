package project.stylemate.dto.style;

import lombok.Builder;
import lombok.Getter;
import project.stylemate.enums.Gender;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class StyleSearchCondition {

    private Gender gender;
    private Integer minHeight;
    private Integer maxHeight;
    private String styleCategory;

}
