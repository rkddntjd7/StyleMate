package project.stylemate.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import project.stylemate.entity.BookMark;
import project.stylemate.entity.Member;
import project.stylemate.entity.Style;
import project.stylemate.exception.SmLogicException;
import project.stylemate.repository.BookMarkRepository;
import project.stylemate.repository.MemberRepository;
import project.stylemate.repository.StyleRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookMarkServiceTest {

    @Mock
    private BookMarkRepository bookMarkRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private StyleRepository styleRepository;

    @InjectMocks
    private BookMarkService bookMarkService;

    @Test
    public void 북마크추가() {
        //given
        Long memberId = 1L;
        Long styleID = 1L;
        Member member = mock(Member.class);
        Style style = mock(Style.class);

        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));
        when(styleRepository.findById(styleID)).thenReturn(Optional.of(style));
        when(bookMarkRepository.findByMemberAndStyle(member, style)).thenReturn(Optional.empty());

        //when
        boolean result = bookMarkService.add(memberId, styleID);

        //then
        assertTrue(result);
        verify(memberRepository, times(1)).findById(memberId);
        verify(styleRepository, times(1)).findById(styleID);
        verify(bookMarkRepository, times(1)).save(any(BookMark.class));
    }

    @Test
    public void 북마크추가_실패() {
        //given
        Long memberId = 1L;
        Long styleID = 1L;
        Member member = mock(Member.class);
        Style style = mock(Style.class);
        BookMark bookMark = mock(BookMark.class);

        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));
        when(styleRepository.findById(styleID)).thenReturn(Optional.of(style));
        when(bookMarkRepository.findByMemberAndStyle(member, style)).thenReturn(Optional.of(bookMark));

        //when
        boolean result = bookMarkService.add(memberId, styleID);

        //then
        assertFalse(result);
        verify(memberRepository, times(1)).findById(memberId);
        verify(styleRepository, times(1)).findById(styleID);
        verify(bookMarkRepository, times(0)).save(any(BookMark.class));
    }

    @Test
    public void 북마크삭제() {
        //given
        Long bookMarkId = 1L;
        BookMark bookMark = mock(BookMark.class);

        when(bookMarkRepository.findById(bookMarkId)).thenReturn(Optional.of(bookMark));

        //when
        bookMarkService.delete(bookMarkId);

        //then
        verify(bookMarkRepository, times(1)).findById(bookMarkId);
        verify(bookMarkRepository, times(1)).delete(bookMark);
    }

    @Test
    public void 북마크삭제_실패() {
        //given
        Long bookMarkId = 1L;
        BookMark bookMark = mock(BookMark.class);

        when(bookMarkRepository.findById(bookMarkId)).thenReturn(Optional.empty());

        //when
        assertThrows(SmLogicException.class, () -> bookMarkService.delete(bookMarkId));

        //then
        verify(bookMarkRepository, times(1)).findById(bookMarkId);
        verify(bookMarkRepository, times(0)).delete(bookMark);
    }
}