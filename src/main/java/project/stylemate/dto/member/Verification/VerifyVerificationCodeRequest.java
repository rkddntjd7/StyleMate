package project.stylemate.dto.member.Verification;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class VerifyVerificationCodeRequest {

    @NotNull
    private String email;

    @NotNull
    private String verificationCode;
}
