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
public class SaveStyleRequest {

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
