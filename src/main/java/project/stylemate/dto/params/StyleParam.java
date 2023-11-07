package project.stylemate.dto.params;

import lombok.Builder;
import lombok.Getter;
import project.stylemate.entity.Member;
import project.stylemate.entity.Style;
import project.stylemate.enums.Gender;

@Getter
@Builder
public class StyleParam {

    private Long memberId;
    private String styleImages;
    private Gender gender;
    private Integer minHeight;
    private Integer maxHeight;
    private String styleCategory;
    private String content;
    private Integer viewCount;
    private Long styleRank;

    public Style toEntity(Member member) {
        return Style.builder()
                .member(member)
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
