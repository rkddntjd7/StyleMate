package project.stylemate.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @NotNull
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "style_id")
    @NotNull
    private Style style;

    @NotNull
    private String content;

    @Builder
    public Comment(Long id, Member member, Style style, String content) {
        this.id = id;
        this.member = member;
        this.style = style;
        this.content = content;
    }

    public void updateContent(String content) {
        this.content = content;
    }



}
