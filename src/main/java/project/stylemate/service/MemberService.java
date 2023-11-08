package project.stylemate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.stylemate.dto.params.MemberParam;
import project.stylemate.entity.Member;
import project.stylemate.enums.ReturnCode;
import project.stylemate.exception.SmLogicException;
import project.stylemate.repository.MemberRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new SmLogicException(ReturnCode.MEMBER_NOT_FOUND));
    }

    @Transactional
    public void save(MemberParam param) {
        Member member = param.toEntity();

        memberRepository.save(member);
    }

    @Transactional
    public void update(MemberParam param) {
        Member member = param.toEntity();

        member.updateMember(
                param.getNickname(),
                param.getUsername(),
                param.getPassword(),
                param.getEmail(),
                param.getName(),
                param.getBirthDate(),
                param.getPhoneNumber(),
                param.getGender(),
                param.getStyleCategory(),
                param.getUserImageUrl(),
                param.getPoint(),
                param.isActive()
        );


    }
}