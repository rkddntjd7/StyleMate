package project.stylemate.dto.member.email;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class VerificationRequest {

    @NotNull
    private String email;

    @NotNull
    private String verificationCode;
}
