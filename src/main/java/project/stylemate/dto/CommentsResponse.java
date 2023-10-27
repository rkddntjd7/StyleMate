package project.stylemate.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.stylemate.entity.Comment;

@Getter
@Setter
@NoArgsConstructor
public class CommentsResponse {
    //TODO: user_image_url 필드

    private Long memberId;

    private Long styleId;

    private String content;

    private String nickname;


    @Builder
    public CommentsResponse(Long memberId, Long styleId, String content, String nickname) {
        this.memberId = memberId;
        this.styleId = styleId;
        this.content = content;
        this.nickname = nickname;
    }

    public static CommentsResponse of(Comment comment) {
        return CommentsResponse.builder()
                .memberId(comment.getMember().getId())
                .styleId(comment.getStyle().getId())
                .content(comment.getContent())
                .nickname(comment.getMember().getNickname())
                .build();
    }

}
