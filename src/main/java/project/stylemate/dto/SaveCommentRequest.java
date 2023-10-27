package project.stylemate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.stylemate.dto.params.SaveCommentParam;

import javax.validation.constraints.NotNull;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveCommentRequest {

    @NotNull
    private String content;

    public SaveCommentParam convert(Long styleId, Long memberId) {
        return SaveCommentParam.builder()
                .content(content)
                .styleId(styleId)
                .memberId(memberId)
                .build();
    }
}
