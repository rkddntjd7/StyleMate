package project.stylemate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import project.stylemate.dto.style.*;
import project.stylemate.dto.common.ApiResponse;
import project.stylemate.dto.common.SmPage;
import project.stylemate.dto.params.UpsertStyleParam;
import project.stylemate.entity.Style;
import project.stylemate.enums.ReturnCode;
import project.stylemate.service.LikeService;
import project.stylemate.service.StyleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class StyleController {

    private final StyleService styleService;
    private final LikeService likeService;

    //API 문서: https://www.notion.so/d6b383b3ef7a4426b1ec5a95f61563f9?pvs=4
    @GetMapping("/api/v1/styles")
    public ApiResponse<?> getAllStyles(StyleSearchRequest request, Pageable pageable) {
        // TODO: member 작업 이후 argument resolver로 받기
        Long memberId = 1L;

        Page<Style> stylePage = styleService.getAllStyles(memberId, request.toCond(), pageable);

        Page<GetAllStylesResponse> styleDtoPage = stylePage.map(GetAllStylesResponse::of);

        return ApiResponse.of(SmPage.of(styleDtoPage));
    }

    //API 문서: https://www.notion.so/a45ae0d712e54294991d680d2d43d425?pvs=4
    @GetMapping("/api/v1/styles/{styleId}")
    public ApiResponse<?> getStyleById(@PathVariable Long styleId) {
        Style style = styleService.getStyleById(styleId);
        Long likeCount = likeService.getLikeCountByStyleId(styleId);

        return ApiResponse.of(GetStyleByIdResponse.of(style, likeCount));
    }

    //API 문서: https://www.notion.so/ac3f277d8db7401f936fee4f1d26b92d?pvs=4
    @PostMapping("/api/v1/styles")
    public ApiResponse<?> save(@RequestBody @Valid SaveStyleRequest request) {
        // TODO: member 작업 이후 argument resolver로 받기
        Long memberId = 1L;

        UpsertStyleParam param = request.toParam(memberId);
        styleService.save(param);

        return ApiResponse.of(ReturnCode.SUCCESS);
    }

    @PatchMapping("/api/v1/styles/{styleId}")
    public ApiResponse<?> update(@PathVariable Long styleId, @RequestBody @Valid UpdateStyleRequest request) {
        // TODO: member 작업 이후 argument resolver로 받기
        Long memberId = 1L;

        UpsertStyleParam param = request.toParam(memberId);
        styleService.update(styleId, param);

        return ApiResponse.of(ReturnCode.SUCCESS);
    }

    //API 문서: https://www.notion.so/39d746f54233493ba8b64330cfa72888?pvs=4
    @DeleteMapping("/api/v1/styles/{styleId}")
    public ApiResponse<?> delete(@PathVariable Long styleId) {
        styleService.delete(styleId);

        return ApiResponse.of(ReturnCode.SUCCESS);
    }

    @PatchMapping("/api/v1/styles/{styleId}/increase-views")
    public ApiResponse<?> increaseViews(@PathVariable Long styleId, HttpServletRequest request, HttpServletResponse response) {
        styleService.increaseViews(styleId, request, response);

        return ApiResponse.of(ReturnCode.SUCCESS);
    }

}
