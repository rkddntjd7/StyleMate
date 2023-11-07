package project.stylemate.entity;

import lombok.*;
import project.stylemate.dto.style.StyleResponse;
import project.stylemate.enums.Gender;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Style extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private LocalDateTime deleteDateTime;

    @Builder
    public Style(Long id, Member member, String styleImages, Gender gender, Integer minHeight, Integer maxHeight, String styleCategory, String content, Integer viewCount, Long styleRank, LocalDateTime deleteDateTime) {
        this.id = id;
        this.member = member;
        this.styleImages = styleImages;
        this.gender = gender;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.styleCategory = styleCategory;
        this.content = content;
        this.viewCount = viewCount;
        this.styleRank = styleRank;
        this.deleteDateTime = deleteDateTime;
    }

    public void updateStyle(String styleImages, Gender gender, Integer minHeight, Integer maxHeight, String styleCategory, String content, Integer viewCount, Long styleRank) {
        this.styleImages = styleImages;
        this.gender = gender;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.styleCategory = styleCategory;
        this.content = content;
        this.viewCount = viewCount;
        this.styleRank = styleRank;
    }

    public void deleteStyle(LocalDateTime deleteDateTime) {
        this.deleteDateTime = deleteDateTime;
    }

    public void increaseViews(Integer viewCount) {
        this.viewCount = viewCount + 1;
    }

}
