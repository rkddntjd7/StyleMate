package project.stylemate.dto.style;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.stylemate.dto.params.SaveUpdateStyleParam;
import project.stylemate.enums.Gender;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStyleRequest {

    private String styleImages;
    private Gender gender;
    private Integer minHeight;
    private Integer maxHeight;
    private String styleCategory;
    private String content;
    private Long styleRank;

    public SaveUpdateStyleParam toParam(Long memberId) {
        return SaveUpdateStyleParam.builder()
                .memberId(memberId)
                .styleImages(styleImages)
                .gender(gender)
                .minHeight(minHeight)
                .maxHeight(maxHeight)
                .styleCategory(styleCategory)
                .content(content)
                .styleRank(styleRank)
                .build();
    }
}
