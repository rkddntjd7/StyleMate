package project.stylemate.dto.member.Verification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerifyVerificationCodeRequest {

    @NotNull
    private String email;

    @NotNull
    private String verificationCode;
}
