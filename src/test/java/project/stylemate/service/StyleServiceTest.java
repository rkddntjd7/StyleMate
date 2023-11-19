package project.stylemate.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.stylemate.dto.params.UpsertStyleParam;
import project.stylemate.dto.style.StyleSearchCondition;
import project.stylemate.entity.Member;
import project.stylemate.entity.Style;
import project.stylemate.exception.SmLogicException;
import project.stylemate.repository.MemberRepository;
import project.stylemate.repository.StyleRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StyleServiceTest {

    @Mock
    private StyleRepository styleRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private StyleService styleService;


    @Test
    public void 전체조회_성공() {
        //given
        Long memberId = 1L;
        StyleSearchCondition condition = mock(StyleSearchCondition.class);
        Pageable pageable = mock(Pageable.class);
        Page<Style> page = mock(Page.class);
        Member member = mock(Member.class);

        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));
        when(styleRepository.getAllStyles(condition, pageable)).thenReturn(page);

        //when
        Page<Style> result = styleService.getAllStyles(memberId, condition, pageable);

        //then
        assertThat(result).isEqualTo(page);
        verify(memberRepository, times(1)).findById(memberId);
        verify(styleRepository, times(1)).getAllStyles(condition, pageable);
    }

    @Test
    public void 전체조회_실패() {
        //given
        Long memberId = 1L;
        StyleSearchCondition condition = mock(StyleSearchCondition.class);
        Pageable pageable = mock(Pageable.class);

        when(memberRepository.findById(memberId)).thenReturn(Optional.empty());

        //when
        assertThrows(SmLogicException.class, () -> styleService.getAllStyles(memberId, condition, pageable));

        //then
        verify(memberRepository, times(1)).findById(memberId);
        verify(styleRepository, times(0)).getAllStyles(condition, pageable);
    }

    @Test
    public void 단건조회_성공() {
        //given
        Long styleId = 1L;

        Style style = mock(Style.class);

        when(styleRepository.findById(styleId)).thenReturn(Optional.of(style));

        //when
        Style result = styleService.getStyleById(styleId);

        //then
        assertThat(result).isEqualTo(style);
        verify(styleRepository, times(1)).findById(styleId);
    }

    @Test
    public void 단건조회_실패() {
        //given
        Long styleId = 1L;

        when(styleRepository.findById(styleId)).thenReturn(Optional.empty());

        //when
        assertThrows(SmLogicException.class, () -> styleService.getStyleById(styleId));

        //then
        verify(styleRepository, times(1)).findById(styleId);
    }

    @Test
    public void 저장_성공() {
        //given
        UpsertStyleParam param = mock(UpsertStyleParam.class);
        Member member = mock(Member.class);

        when(memberRepository.findById(param.getMemberId())).thenReturn(Optional.of(member));

        //when
        Style style = param.toEntity(member);
        styleService.save(param);

        //then
        verify(memberRepository, times(1)).findById(param.getMemberId());
        verify(styleRepository, times(1)).save(style);
    }

    @Test
    public void 저장_실패() {
        //given
        UpsertStyleParam param = mock(UpsertStyleParam.class);
        Member member = mock(Member.class);

        when(memberRepository.findById(param.getMemberId())).thenReturn(Optional.empty());

        //when
        Style style = param.toEntity(member);
        assertThrows(SmLogicException.class, () -> styleService.save(param));

        //then
        verify(memberRepository, times(1)).findById(param.getMemberId());
        verify(styleRepository, times(0)).save(style);
    }

    @Test
    public void 수정_성공() {
        //given
        Long styleId = 1L;
        UpsertStyleParam param = mock(UpsertStyleParam.class);
        Style style = mock(Style.class);

        when(styleRepository.findById(styleId)).thenReturn(Optional.of(style));

        //when
        styleService.update(styleId, param);

        //then
        verify(styleRepository, times(1)).findById(styleId);
        verify(style, times(1)).update(
                param.getStyleImages(),
                param.getGender(),
                param.getMinHeight(),
                param.getMaxHeight(),
                param.getStyleCategory(),
                param.getContent(),
                param.getStyleRank()
        );
    }

    @Test
    public void 수정_실패() {
        //given
        Long styleId = 1L;
        UpsertStyleParam param = mock(UpsertStyleParam.class);
        Style style = mock(Style.class);

        when(styleRepository.findById(styleId)).thenReturn(Optional.empty());

        //when
        assertThrows(SmLogicException.class, () -> styleService.update(styleId, param));

        //then
        verify(styleRepository, times(1)).findById(styleId);
        verify(style, times(0)).update(
                param.getStyleImages(),
                param.getGender(),
                param.getMinHeight(),
                param.getMaxHeight(),
                param.getStyleCategory(),
                param.getContent(),
                param.getStyleRank()
        );

    }

    @Test
    public void 삭제_성공() {
        //given
        Long styleId = 1L;
        Style style = mock(Style.class);

        when(styleRepository.findById(styleId)).thenReturn(Optional.of(style));

        //when
        styleService.delete(styleId);

        //then
        verify(styleRepository, times(1)).findById(styleId);
        verify(style, times(1)).delete(any(LocalDateTime.class));
    }

    @Test
    public void 삭제_실패() {
        //given
        Long styleId = 1L;
        Style style = mock(Style.class);

        when(styleRepository.findById(styleId)).thenReturn(Optional.empty());

        //when
        assertThrows(SmLogicException.class, () -> styleService.delete(styleId));

        //then
        verify(styleRepository, times(1)).findById(styleId);
        verify(style, times(0)).delete(any(LocalDateTime.class));
    }

    @Test
    public void 조회수증가_성공() {
        //given
        Long styleId = 1L;
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Style style = mock(Style.class);

        when(styleRepository.findById(styleId)).thenReturn(Optional.of(style));

        //when
        styleService.increaseViews(styleId, request, response);

        //then
        verify(styleRepository, times(1)).findById(styleId);
        verify(style, times(1)).increaseViews(anyInt());
    }

    @Test
    public void 조회수증가_실패() {
        //given
        Long styleId = 1L;
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Style style = mock(Style.class);

        when(styleRepository.findById(styleId)).thenReturn(Optional.empty());

        //when
        assertThrows(SmLogicException.class, () -> styleService.increaseViews(styleId, request, response));

        //then
        verify(styleRepository, times(1)).findById(styleId);
        verify(style, times(0)).increaseViews(anyInt());
    }
}