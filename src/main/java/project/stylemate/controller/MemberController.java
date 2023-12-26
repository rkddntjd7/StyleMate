package project.stylemate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.stylemate.dto.common.ApiResponse;
import project.stylemate.dto.member.CheckUsernameRequest;
import project.stylemate.enums.ReturnCode;
import project.stylemate.service.MemberService;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //API 문서: https://www.notion.so/fe967ba5c2b640aaae1b3d83c0fd9dbb?pvs=4
    @PostMapping("/api/v1/users/check-username")
    ApiResponse<?> checkUsernameDuplication(@RequestBody CheckUsernameRequest request) {

        memberService.checkUsernameDuplication(request.getUsername());

        return ApiResponse.of(ReturnCode.SUCCESS);
    }
}
