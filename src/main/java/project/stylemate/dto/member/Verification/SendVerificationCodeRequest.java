package project.stylemate.dto.member.Verification;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class SendVerificationCodeRequest {

    @NotNull
    private String email;
}
