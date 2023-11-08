package project.stylemate.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.stylemate.enums.Gender;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    @NotNull
    private String nickname;

    @Column(length = 20)
    @NotNull
    private String username;

    @Column(length = 20)
    @NotNull
    private String password;

    @Column(length = 50)
    @NotNull
    private String email;

    @Column(length = 20)
    @NotNull
    private String name;

    @NotNull
    private LocalDate birthDate;

    @Column(length = 20)
    @NotNull
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    @NotNull
    private Gender gender;

    @Column(length = 50)
    @NotNull
    private String styleCategory;

    @NotNull
    private String userImageUrl;

    @NotNull
    private Integer point;

    @NotNull
    private boolean isActive;

    @Builder
    public Member(Long id, String nickname, String username, String password, String email, String name, LocalDate birthDate, String phoneNumber, Gender gender, String styleCategory, String userImageUrl, Integer point, boolean isActive) {
        this.id = id;
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.styleCategory = styleCategory;
        this.userImageUrl = userImageUrl;
        this.point = point;
        this.isActive = isActive;
    }

    public void updateMember(String nickname, String username, String password, String email, String name, LocalDate birthDate, String phoneNumber, Gender gender, String styleCategory, String userImageUrl, Integer point, boolean isActive) {
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.styleCategory = styleCategory;
        this.userImageUrl = userImageUrl;
        this.point = point;
        this.isActive = isActive;
    }
}
