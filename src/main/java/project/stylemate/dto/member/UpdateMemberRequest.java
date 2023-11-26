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
public class UpdateMemberRequest {


    private String nickname;
    private String username;
    private String password;
    private String email;
    private String name;
    private LocalDate birthDate;
    private String phoneNumber;
    private Gender gender;
    private String styleCategory;
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
