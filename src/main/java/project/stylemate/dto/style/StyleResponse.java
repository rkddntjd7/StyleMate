package project.stylemate.dto.style;

import lombok.Builder;
import lombok.Getter;
import project.stylemate.entity.Like;
import project.stylemate.entity.Style;
import project.stylemate.enums.Gender;

@Getter
@Builder
public class StyleResponse {
    //TODO: user_image_url 필드
    private Long memberId;
    private String nickname;
    private String styleImages;
    private Gender gender;
    private Integer minHeight;
    private Integer maxHeight;
    private String styleCategory;
    private String content;
    private Integer viewCount;
    private Long styleRank;

    private Long likeCount;


    public static StyleResponse of(Style style, Long likeCount) {
        return StyleResponse.builder()
                .memberId(style.getMember().getId())
                .nickname(style.getMember().getNickname())
                .styleImages(style.getStyleImages())
                .gender(style.getGender())
                .minHeight(style.getMinHeight())
                .maxHeight(style.getMaxHeight())
                .styleCategory(style.getStyleCategory())
                .content(style.getContent())
                .viewCount((int) style.getViewCount())
                .styleRank(style.getStyleRank())
                .likeCount(likeCount)
                .build();
    }

}
