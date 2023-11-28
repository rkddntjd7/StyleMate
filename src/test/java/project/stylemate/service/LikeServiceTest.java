package project.stylemate.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import project.stylemate.entity.Like;
import project.stylemate.entity.Member;
import project.stylemate.entity.Style;
import project.stylemate.enums.ReturnCode;
import project.stylemate.exception.SmLogicException;
import project.stylemate.repository.LikeRepository;
import project.stylemate.repository.MemberRepository;
import project.stylemate.repository.StyleRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LikeServiceTest {
    @Mock
    private LikeRepository likeRepository;

    @Mock
    private StyleRepository styleRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private LikeService likeService;

    @Test
    public void 좋아요성공() {
        //given
        Member member = mock(Member.class);
        Style style = mock(Style.class);

        when(memberRepository.findById(member.getId())).thenReturn(Optional.of(member));
        when(styleRepository.findById(style.getId())).thenReturn(Optional.of(style));
        when(likeRepository.findByMemberAndStyle(member, style)).thenReturn(Optional.empty());

        //when
        likeService.addLike(member.getId(), style.getId());

        //then
        verify(memberRepository, times(1)).findById(member.getId());
        verify(styleRepository, times(1)).findById(style.getId());
        verify(likeRepository, times(1)).findByMemberAndStyle(member, style);
        verify(likeRepository, times(1)).save(any(Like.class));
    }

    @Test
    public void 좋아요실패() {
        //given
        Member member = mock(Member.class);
        Style style = mock(Style.class);
        Like like = mock(Like.class);

        when(memberRepository.findById(member.getId())).thenReturn(Optional.of(member));
        when(styleRepository.findById(style.getId())).thenReturn(Optional.of(style));
        when(likeRepository.findByMemberAndStyle(member, style)).thenReturn(Optional.of(like));

        //when
        likeService.addLike(member.getId(), style.getId());

        //then
        verify(memberRepository, times(1)).findById(member.getId());
        verify(styleRepository, times(1)).findById(style.getId());
        verify(likeRepository, times(1)).findByMemberAndStyle(member, style);
        verify(likeRepository, times(0)).save(like);
    }

    @Test
    public void 좋아요취소_성공() {
        //given
        Long likeId = 1L;
        Like like = mock(Like.class);

        when(likeRepository.findById(likeId)).thenReturn(Optional.of(like));

        //when
        likeService.delete(likeId);

        //then
        verify(likeRepository, times(1)).findById(likeId);
        verify(likeRepository, times(1)).delete(like);
    }

    @Test
    public void 좋아요취소_실패() {
        //given
        Long likeId = 1L;
        Like like = mock(Like.class);

        when(likeRepository.findById(likeId)).thenReturn(Optional.empty());

        //when
        assertThrows(SmLogicException.class, () -> likeService.delete(likeId));

        //then
        verify(likeRepository, times(1)).findById(likeId);
        verify(likeRepository, times(0)).delete(like);

    }

}