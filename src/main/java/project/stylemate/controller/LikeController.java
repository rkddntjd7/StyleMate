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

    @PostMapping("/api/v1/styles/{styleId}/favorites")
    public ApiResponse<?> addLike(@PathVariable Long styleId) {
        // TODO: member 작업 이후 argument resolver로 받기
        Long memberId = 1L;

        likeService.addLike(memberId, styleId);

        return ApiResponse.of(ReturnCode.SUCCESS);
    }

    @DeleteMapping("/api/v1/styles/{styleId}/{likeId}")
    public ApiResponse<?> delete(@PathVariable Long styleId,
                                 @PathVariable Long likeId) {
        likeService.delete(likeId);

        return ApiResponse.of(ReturnCode.SUCCESS);
    }

}
