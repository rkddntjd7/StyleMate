package project.stylemate.dto.params;

import lombok.Builder;
import lombok.Getter;
import project.stylemate.entity.Member;
import project.stylemate.enums.Gender;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Builder
public class MemberParam {

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

    public Member toEntity() {
        return Member.builder()
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