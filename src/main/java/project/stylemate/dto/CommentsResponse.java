package project.stylemate.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentsResponse {

    private Long memberId;
    private Long styleId;
    private String content;

    @Builder
    public CommentsResponse(Long memberId, Long styleId, String content) {
        this.memberId = memberId;
        this.styleId = styleId;
        this.content = content;
    }

}
