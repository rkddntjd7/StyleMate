package project.stylemate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.stylemate.dto.common.ApiResponse;
import project.stylemate.enums.ReturnCode;
import project.stylemate.service.BookMarkService;

@RestController
@RequiredArgsConstructor
public class BookMarkController {

    private final BookMarkService bookMarkService;

    @PostMapping("/api/v1/styles/{styleId}/bookmark")
    public ApiResponse<?> add(@PathVariable Long styleId) {
        // TODO: member 작업 이후 argument resolver로 받기
        Long memberId = 1L;

        bookMarkService.add(memberId, styleId);

        return ApiResponse.of(ReturnCode.SUCCESS);
    }

    @DeleteMapping("/api/v1/styles/{styleId}/bookmarks/{bookMarkId}")
    //styleId는 사용하지 않는 변수지만 rest api 표준을 위해 명시
    public ApiResponse<?> delete(@PathVariable Long styleId,
                                 @PathVariable Long bookMarkId
    ) {
        bookMarkService.delete(bookMarkId);

        return ApiResponse.of(ReturnCode.SUCCESS);
    }
}
