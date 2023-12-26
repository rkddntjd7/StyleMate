package project.stylemate.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import project.stylemate.entity.Member;
import project.stylemate.exception.SmLogicException;
import project.stylemate.repository.MemberRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    public void 아이디중복검증() {
        //given
        String username = "rkddntjd7";

        when(memberRepository.findByUsername(username)).thenReturn(null);

        //when
        memberService.checkUsernameDuplication(username);

        //then
        verify(memberRepository, times(1)).findByUsername(username);
    }

    @Test
    public void 아이디중복검증_실패() {
        //given
        String username = "rkddntjd7";
        Member member = mock(Member.class);

        when(memberRepository.findByUsername(username)).thenReturn(member);

        //when
        assertThrows(SmLogicException.class, () -> memberService.checkUsernameDuplication(username));

        //then
        verify(memberRepository, times(1)).findByUsername(username);
    }
}