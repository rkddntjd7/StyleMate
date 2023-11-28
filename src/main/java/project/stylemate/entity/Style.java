package project.stylemate.entity;

import lombok.*;
import project.stylemate.enums.Gender;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Style extends BaseEntity {

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
    private Long styleRank;

    @NotNull
    private long viewCount = 0;

    private LocalDateTime deleteDateTime;

    @Builder
    public Style(
            Long id,
            Member member,
            String styleImages,
            Gender gender,
            Integer minHeight,
            Integer maxHeight,
            String styleCategory,
            String content,
            Long styleRank,
            LocalDateTime deleteDateTime
    ) {
        this.id = id;
        this.member = member;
        this.styleImages = styleImages;
        this.gender = gender;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.styleCategory = styleCategory;
        this.content = content;
        this.styleRank = styleRank;
        this.deleteDateTime = deleteDateTime;
    }

    public void update(
            String styleImages,
            Gender gender,
            Integer minHeight,
            Integer maxHeight,
            String styleCategory,
            String content,
            Long styleRank
    ) {
        if (styleImages != null && !styleImages.equals(this.styleImages)) {
            this.styleImages = styleImages;
        }
        if (gender != null && !gender.equals(this.gender)) {
            this.gender = gender;
        }
        if (minHeight != null && !minHeight.equals(this.minHeight)) {
            this.minHeight = minHeight;
        }
        if (maxHeight != null && !maxHeight.equals(this.maxHeight)) {
            this.maxHeight = maxHeight;
        }
        if (styleCategory != null && !styleCategory.equals(this.styleCategory)) {
            this.styleCategory = styleCategory;
        }
        if (content != null && !content.equals(this.content)) {
            this.content = content;
        }
        if (styleRank != null && !styleRank.equals(this.styleRank)) {
            this.styleRank = styleRank;
        }

    }

    public void delete(LocalDateTime deleteDateTime) {
        this.deleteDateTime = deleteDateTime;
    }

    public void increaseViews(Integer viewCount) {
        this.viewCount = viewCount + 1;
    }

}
