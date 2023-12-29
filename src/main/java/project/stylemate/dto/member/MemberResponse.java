package project.stylemate.dto.member;

import lombok.Builder;
import lombok.Getter;
import project.stylemate.entity.Member;
import project.stylemate.enums.Gender;

import java.time.LocalDate;


@Getter
@Builder
public class MemberResponse {

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
    private Integer point;

    public MemberResponse of(Member member) {
        return MemberResponse.builder()
                .nickname(member.getNickname())
                .username(member.getUsername())
                .password(member.getPassword())
                .email(member.getEmail())
                .name(member.getName())
                .birthDate(member.getBirthDate())
                .phoneNumber(member.getPhoneNumber())
                .gender(member.getGender())
                .styleCategory(member.getStyleCategory())
                .userImageUrl(member.getUserImageUrl())
                .point(member.getPoint())
                .build();
    }
}
