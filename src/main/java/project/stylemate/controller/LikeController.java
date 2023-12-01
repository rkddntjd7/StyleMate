package project.stylemate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import project.stylemate.dto.common.ApiResponse;
import project.stylemate.enums.ReturnCode;
import project.stylemate.service.LikeService;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    //API 문서: https://www.notion.so/43e7ec9848194eeaba674dfca2f20d83?pvs=4
    @PostMapping("/api/v1/styles/{styleId}/likes")
    public ApiResponse<?> addLike(@PathVariable Long styleId) {
        // TODO: member 작업 이후 argument resolver로 받기
        Long memberId = 1L;

        likeService.addLike(memberId, styleId);

        return ApiResponse.of(ReturnCode.SUCCESS);
    }

    //API 문서: https://www.notion.so/6639d07047474f02808f77b306b5382f?pvs=4
    @DeleteMapping("/api/v1/styles/{styleId}/likes/{likeId}")
    public ApiResponse<?> delete(@PathVariable Long styleId,
                                 @PathVariable Long likeId) {
        likeService.delete(likeId);

        return ApiResponse.of(ReturnCode.SUCCESS);
    }

}
