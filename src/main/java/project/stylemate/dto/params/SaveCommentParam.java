package project.stylemate.dto.params;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.stylemate.entity.Comment;
import project.stylemate.entity.Member;
import project.stylemate.entity.Style;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SaveCommentParam {
    private Long memberId;
    private String content;
    private Long styleId;

    public Comment toEntity(Style style, Member member) {
        return Comment.builder()
                .style(style)
                .member(member)
                .content(content)
                .build();
    }
}
