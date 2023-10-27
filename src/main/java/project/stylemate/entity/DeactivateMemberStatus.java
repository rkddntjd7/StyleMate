package project.stylemate.entity;

import lombok.Getter;
import project.stylemate.enums.MemberStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
public class DeactivateMemberStatus extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @NotNull
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    @NotNull
    private MemberStatus status;


}
