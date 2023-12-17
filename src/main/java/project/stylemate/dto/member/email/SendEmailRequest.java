package project.stylemate.dto.member.email;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class SendEmailRequest {

    @NotNull
    private String email;
}
