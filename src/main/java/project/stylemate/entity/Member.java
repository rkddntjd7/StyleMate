package project.stylemate.entity;

import lombok.Getter;
import lombok.Setter;
import project.stylemate.enums.Gender;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Member extends BaseEntity{

    @Id
    @GeneratedValue
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


}
