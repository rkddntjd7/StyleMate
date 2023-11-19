package project.stylemate.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import project.stylemate.entity.Comment;
import project.stylemate.entity.Member;
import project.stylemate.entity.Style;
import project.stylemate.exception.SmLogicException;
import project.stylemate.repository.CommentRepository;
import project.stylemate.repository.MemberRepository;
import project.stylemate.repository.StyleRepository;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private StyleRepository styleRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private CommentService commentService;

    @Test
    public void 댓글조회_성공() {
        //given
        Long styleId = 1L;
        Pageable pageable = mock(Pageable.class);
        Page<Comment> page = mock(Page.class);
        Style style = mock(Style.class);

        when(styleRepository.findById(styleId)).thenReturn(Optional.of(style));
        when(commentRepository.findByStyleId(styleId, pageable)).thenReturn(page);

        //when
        Page<Comment> result = commentService.getAllCommentsByStyleId(styleId, pageable);

        //then
        assertThat(result).isEqualTo(page);
        verify(styleRepository, times(1)).findById(styleId);
        verify(commentRepository, times(1)).findByStyleId(styleId, pageable);
    }

    @Test
    public void 댓글조회_실패() {
        //given
        Long styleId = 1L;

        when(styleRepository.findById(styleId)).thenReturn(Optional.empty());

        //when
        assertThrows(SmLogicException.class, () -> commentService.getAllCommentsByStyleId(styleId, null));

        //then
        verify(styleRepository, times(1)).findById(styleId);
        verify(commentRepository, times(0)).findByStyleId(any(), any());
    }

    @Test
    public void 댓글저장_성공() {
        //given
        Long styleId = 1L;
        String content = "aaa";
        Long memberId = 1L;
        Style style = mock(Style.class);
        Member member = mock(Member.class);

        when(styleRepository.findById(styleId)).thenReturn(Optional.of(style));
        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));

        //when
        commentService.save(styleId, content, memberId);

        //then
        verify(styleRepository, times(1)).findById(styleId);
        verify(memberRepository, times(1)).findById(memberId);
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    public void 댓글저장_실패_스타일() {
        //given
        Long styleId = 1L;
        String content = "aaa";
        Long memberId = 1L;

        when(styleRepository.findById(styleId)).thenReturn(Optional.empty());

        //when
        assertThrows(SmLogicException.class, () -> commentService.save(styleId, content, memberId));

        //then
        verify(styleRepository, times(1)).findById(styleId);
        verify(memberRepository, times(0)).findById(memberId);
        verify(commentRepository, times(0)).save(any());

    }

    @Test
    public void 댓글저장_실패_멤버() {
        //given
        Long styleId = 1L;
        String content = "aaa";
        Long memberId = 1L;

        when(styleRepository.findById(styleId)).thenReturn(Optional.of(new Style()));
        when(memberRepository.findById(memberId)).thenReturn(Optional.empty());

        //when
        assertThrows(SmLogicException.class, () -> commentService.save(styleId, content, memberId));

        //then
        verify(styleRepository, times(1)).findById(styleId);
        verify(memberRepository, times(1)).findById(memberId);
        verify(commentRepository, times(0)).save(any());

    }

    @Test
    public void 댓글수정_성공() {
        //given
        Long commentId = 1L;
        String content = "test content";

        Comment comment = mock(Comment.class);

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));

        //when
        commentService.update(commentId, content);

        //then
        verify(commentRepository, times(1)).findById(commentId);
        verify(comment, times(1)).updateContent(content);
    }

    @Test
    public void 댓글수정_실패() {
        //given
        Long commentId = 1L;
        String content = "test content";

        Comment comment = mock(Comment.class);

        when(commentRepository.findById(commentId)).thenReturn(Optional.empty());

        //when
        assertThrows(SmLogicException.class, () -> commentService.update(commentId, content));

        //then
        verify(commentRepository, times(1)).findById(commentId);
        verify(comment, times(0)).updateContent(content);
    }

    @Test
    public void 댓글삭제_성공() {
        //given
        Long commentId = 1L;

        Comment comment = mock(Comment.class);

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));

        //when
        commentService.delete(commentId);

        //then
        verify(commentRepository, times(1)).findById(commentId);
        verify(commentRepository, times(1)).delete(comment);
    }

    @Test
    public void 댓글삭제_실패() {
        //given
        Long commentId = 1L;

        Comment comment = mock(Comment.class);

        when(commentRepository.findById(commentId)).thenReturn(Optional.empty());

        //when
        assertThrows(SmLogicException.class, () -> commentService.delete(commentId));

        //then
        verify(commentRepository, times(1)).findById(commentId);
        verify(commentRepository, times(0)).delete(comment);
    }

}