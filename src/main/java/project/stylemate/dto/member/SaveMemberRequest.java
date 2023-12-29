package project.stylemate.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.stylemate.dto.params.MemberParam;
import project.stylemate.enums.Gender;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveMemberRequest {

    @NotNull
    private String nickname;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String email;
    @NotNull
    private String name;
    @NotNull
    private LocalDate birthDate;
    @NotNull
    private String phoneNumber;
    @NotNull
    private Gender gender;
    @NotNull
    private String styleCategory;
    @NotNull
    private String userImageUrl;

    public MemberParam toParam() {
        return MemberParam.builder()
                .nickname(nickname)
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .birthDate(birthDate)
                .phoneNumber(phoneNumber)
                .gender(gender)
                .styleCategory(styleCategory)
                .userImageUrl(userImageUrl)
                .build();
    }
}
