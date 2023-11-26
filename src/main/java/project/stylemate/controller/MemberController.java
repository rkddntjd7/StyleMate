package project.stylemate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.stylemate.dto.common.ApiResponse;
import project.stylemate.dto.member.MemberResponse;
import project.stylemate.dto.member.SaveMemberRequest;
import project.stylemate.dto.member.UpdateMemberRequest;
import project.stylemate.dto.params.MemberParam;
import project.stylemate.entity.Member;
import project.stylemate.enums.ReturnCode;
import project.stylemate.service.MemberService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //API 문서: https://www.notion.so/f21fa02842a84630a1c2c43f27a5b25b?pvs=4
    @GetMapping("/api/v1/users/{userId}")
    public ApiResponse<?> getMemberById(@PathVariable Long userId, MemberResponse response) {
        Member member = memberService.getMemberById(userId);

        MemberResponse memberResponse = response.of(member);

        return ApiResponse.of(memberResponse);
    }

    //API 문서: https://www.notion.so/597428f907034bd98b7a96555449f4a0?pvs=4
    @PostMapping("/api/v1/users/signup")
    public ApiResponse<?> save(@RequestBody @Valid SaveMemberRequest request) {
        MemberParam param = request.toParam();

        memberService.save(param);

        return ApiResponse.of(ReturnCode.SUCCESS);
    }

    //API 문서: https://www.notion.so/722f3b19a4ce49769fc7d79e3f8599d3?pvs=4
    @PatchMapping("/api/v1/users/{userId}")
    public ApiResponse<?> update(@PathVariable Long userId, @RequestBody UpdateMemberRequest request) {
        MemberParam param = request.toParam();

        memberService.update(userId, param);

        return ApiResponse.of(ReturnCode.SUCCESS);
    }

    //API 문서: https://www.notion.so/38975be161c5492b945c1232f703b0a4?pvs=4
    @DeleteMapping("/api/v1/users/{userId}")
    public ApiResponse<?> delete(@PathVariable Long userId) {
        memberService.delete(userId);

        return ApiResponse.of(ReturnCode.SUCCESS);
    }
}
