package project.stylemate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.stylemate.dto.params.MemberParam;
import project.stylemate.entity.Member;
import project.stylemate.enums.ReturnCode;
import project.stylemate.exception.SmLogicException;
import project.stylemate.repository.MemberRepository;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member getMemberById(Long userId) {
        return memberRepository.findById(userId)
                .orElseThrow(() -> new SmLogicException(ReturnCode.MEMBER_NOT_FOUND));
    }

    @Transactional
    public void save(MemberParam param) {
        Member member = param.toEntity();

        memberRepository.save(member);
    }

    @Transactional
    public void update(Long userId, MemberParam param) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new SmLogicException(ReturnCode.MEMBER_NOT_FOUND));

        member.update(
                param.getNickname(),
                param.getUsername(),
                param.getPassword(),
                param.getEmail(),
                param.getName(),
                param.getBirthDate(),
                param.getPhoneNumber(),
                param.getGender(),
                param.getStyleCategory(),
                param.getUserImageUrl()
        );

    }

    @Transactional
    public void delete(Long userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new SmLogicException(ReturnCode.MEMBER_NOT_FOUND));

        member.delete(LocalDateTime.now());
    }
}