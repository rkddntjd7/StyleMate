package project.stylemate.dto.style;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.stylemate.dto.params.StyleParam;
import project.stylemate.enums.Gender;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStyleRequest {
    @NotNull
    private String styleImages;
    @NotNull
    private Gender gender;
    @NotNull
    private Integer minHeight;
    @NotNull
    private Integer maxHeight;
    @NotNull
    private String styleCategory;
    @NotNull
    private String content;
    @NotNull
    private Integer viewCount;
    @NotNull
    private Long styleRank;

    public StyleParam toParam(Long memberId) {
        return StyleParam.builder()
                .memberId(memberId)
                .styleImages(styleImages)
                .gender(gender)
                .minHeight(minHeight)
                .maxHeight(maxHeight)
                .styleCategory(styleCategory)
                .content(content)
                .viewCount(viewCount)
                .styleRank(styleRank)
                .build();
    }
}
