package project.stylemate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import project.stylemate.dto.common.ApiResponse;
import project.stylemate.dto.CommentsResponse;
import project.stylemate.dto.SaveCommentRequest;
import project.stylemate.dto.UpdateCommentRequest;
import project.stylemate.dto.common.SmPage;
import project.stylemate.dto.params.SaveCommentParam;
import project.stylemate.entity.Comment;
import project.stylemate.enums.ReturnCode;
import project.stylemate.repository.MemberRepository;
import project.stylemate.service.CommentService;


@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final MemberRepository memberRepository;

    //API 문서: https://www.notion.so/c8158d6f592c4bfea6f9a56151bdcdbd?pvs=4
    //없는 스타일로 조회해도 요청에 성공함
    @GetMapping("/api/v1/styles/{styleId}/comments")
    public ApiResponse<?> getAllCommentsByStyleId(@PathVariable Long styleId,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {

        Page<Comment> commentsPage = commentService.getAllCommentsByStyleId(styleId, PageRequest.of(page, size));

        Page<CommentsResponse> commentDtoPage = commentsPage.map(Comment::toDto);

        return ApiResponse.of(SmPage.of(commentDtoPage));
    }


    //API 문서: https://www.notion.so/5ee5e58a815e4b92ac9b2a56bffad95a?pvs=4
    @PostMapping("/api/v1/styles/{styleId}/comments")
    public ApiResponse<?> save(@PathVariable Long styleId, @RequestBody SaveCommentRequest saveCommentRequest) {
        // TODO: member 작업 이후 argument resolver로 받기
        Long memberId = 1L;

        commentService.save(styleId, saveCommentRequest.getContent(), memberId);

        return ApiResponse.of(ReturnCode.SUCCESS);
    }

    @PostMapping("/api/v2/styles/{styleId}/comments")
    public ApiResponse<?> saveWithParams(@PathVariable Long styleId, @RequestBody SaveCommentRequest saveCommentRequest) {
        // TODO: member 작업 이후 argument resolver로 받기
        Long memberId = 1L;

        SaveCommentParam param = saveCommentRequest.convert(styleId, memberId);
        commentService.saveWithParams(param);

        return ApiResponse.of(ReturnCode.SUCCESS);
    }

    //API 문서: https://www.notion.so/95d70842df3248e18ddc258050d2e3ed?pvs=4
    @PatchMapping("/api/v1/styles/{styleId}/comments/{commentsId}")
    public ApiResponse<?> update(@PathVariable Long styleId,
                                 @PathVariable Long commentsId,
                                 @RequestBody UpdateCommentRequest updateCommentRequest) {

        Long memberId = 1L;

        commentService.update(commentsId, styleId, memberId, updateCommentRequest.getContent());

        return ApiResponse.of(ReturnCode.SUCCESS);

    }

    //API 문서: https://www.notion.so/49edea55f7db47fa971bc627a3638739?pvs=4
    @DeleteMapping("/api/v1/styles/{styleId}/comments/{commentsId}")
    public ApiResponse<?> delete(@PathVariable Long styleId,
                                 @PathVariable Long commentsId) {

        commentService.delete(commentsId, styleId);

        return ApiResponse.of(ReturnCode.SUCCESS);

    }


}
