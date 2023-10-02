package project.stylemate.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class StyleItem extends BaseEntity{

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "style_id")
    @NotNull
    private Style style;

    @Column(length = 100)
    private String topItem;
    @Column(length = 100)
    private String bottomItem;
    @Column(length = 100)
    private String shoesItem;
    @Column(length = 100)
    private String accessoryItem;

}
