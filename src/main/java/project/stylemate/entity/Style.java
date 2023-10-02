package project.stylemate.entity;

import lombok.Getter;
import lombok.Setter;
import project.stylemate.enums.Gender;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class Style extends BaseEntity{

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @NotNull
    private Member member;
    @NotNull
    private String styleImages;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    @NotNull
    private Gender gender;
    @NotNull
    private Integer minHeight;
    @NotNull
    private Integer maxHeight;
    @Column(length = 50)
    @NotNull
    private String styleCategory;

    @NotNull
    private String content;
    @NotNull
    private Integer viewCount;
    @NotNull
    private Long styleRank;

}
