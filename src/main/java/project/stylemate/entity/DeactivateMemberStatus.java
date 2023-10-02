package project.stylemate.entity;

import lombok.Getter;
import lombok.Setter;
import project.stylemate.enums.Status;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class DeactivateMemberStatus extends BaseEntity{

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @NotNull
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    @NotNull
    private Status status;


}
