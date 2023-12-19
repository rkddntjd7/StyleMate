package project.stylemate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.stylemate.enums.ReturnCode;
import project.stylemate.exception.SmLogicException;
import project.stylemate.repository.MemberRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void checkUsernameDuplication(String username) {
        if (memberRepository.findByUsername(username) != null) {
            throw new SmLogicException(ReturnCode.USERNAME_ALREADY_EXISTS);
        }
    }
}
