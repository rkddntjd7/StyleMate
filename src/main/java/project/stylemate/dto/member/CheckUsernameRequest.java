package project.stylemate.dto.member;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class CheckUsernameRequest {

    @NotNull
    private String username;
}
