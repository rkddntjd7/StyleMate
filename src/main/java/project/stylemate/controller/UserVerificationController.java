package project.stylemate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.stylemate.dto.common.ApiResponse;
import project.stylemate.dto.member.Verification.SendVerificationCodeRequest;
import project.stylemate.dto.member.Verification.VerifyVerificationCodeRequest;
import project.stylemate.enums.ReturnCode;
import project.stylemate.service.EmailService;
import project.stylemate.service.VerificationService;

@RestController
@RequiredArgsConstructor
public class UserVerificationController {

    private final EmailService emailService;
    private final VerificationService verificationService;

    //API 문서: https://www.notion.so/fa161a63750e4df3b545db23c848d24e?pvs=4
    @PostMapping("/api/v1/users/send-verification-code")
    ApiResponse<?> sendVerificationCode(@RequestBody SendVerificationCodeRequest request) {
        String verificationCode = verificationService.generateVerificationCode();

        emailService.sendEmail(request.getEmail(), "이메일 인증", "인증 번호: " + verificationCode);

        verificationService.saveVerificationCode(request.getEmail(), verificationCode);

        return ApiResponse.of(ReturnCode.SUCCESS);
    }

    //API 문서: https://www.notion.so/064df8f270fb4598a5c6ec316a7bf7e8?pvs=4
    @PostMapping("/api/v1/users/verify")
    ApiResponse<?> verifyVerificationCode(@RequestBody VerifyVerificationCodeRequest request) {
        verificationService.verifyEmail(request.getEmail(), request.getVerificationCode());

        return ApiResponse.of(ReturnCode.SUCCESS);
    }
}
