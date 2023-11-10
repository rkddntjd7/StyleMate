package project.stylemate.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import project.stylemate.enums.Gender;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseEntity {

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

    @Column(length = 20)
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
    private Integer point = 0;

    @NotNull
    private boolean isActive = true;

    private LocalDateTime deleteDateTime;

    @Builder
    public Member(Long id, String nickname, String username, String password, String email, String name, LocalDate birthDate, String phoneNumber, Gender gender, String styleCategory, String userImageUrl) {
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
    }

    public void update(
            String nickname,
            String username,
            String password,
            String email,
            String name,
            LocalDate birthDate,
            String phoneNumber,
            Gender gender,
            String styleCategory,
            String userImageUrl
    ) {
        if (nickname != null && !nickname.equals(this.nickname)) {
            this.nickname = nickname;
        }
        if (username != null && !username.equals(this.username)) {
            this.username = username;
        }
        if (password != null && !password.equals(this.password)) {
            this.password = password;
        }
        if (email != null && !email.equals(this.email)) {
            this.email = email;
        }
        if (name != null && !name.equals(this.name)) {
            this.name = name;
        }
        if (birthDate != null && !birthDate.equals(this.birthDate)) {
            this.birthDate = birthDate;
        }
        if (phoneNumber != null && !phoneNumber.equals(this.phoneNumber)) {
            this.phoneNumber = phoneNumber;
        }
        if (gender != null && !gender.equals(this.gender)) {
            this.gender = gender;
        }
        if (styleCategory != null && !styleCategory.equals(this.styleCategory)) {
            this.styleCategory = styleCategory;
        }
        if (userImageUrl != null && !userImageUrl.equals(this.userImageUrl)) {
            this.userImageUrl = userImageUrl;
        }
    }

    public void delete(LocalDateTime deleteDateTime) {
        this.deleteDateTime = deleteDateTime;
    }
}
