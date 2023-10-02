package project.stylemate.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class StyleCategory extends BaseEntity{

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 20)
    @NotNull
    private String categoryName;

}
