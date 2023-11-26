//package project.stylemate.service;
//
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import project.stylemate.dto.params.SaveCommentParam;
//import project.stylemate.entity.Comment;
//import project.stylemate.entity.Member;
//import project.stylemate.entity.Style;
//import project.stylemate.repository.CommentRepository;
//import project.stylemate.repository.MemberRepository;
//import project.stylemate.repository.StyleRepository;
//
//import java.util.Collections;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class CommentServiceTest {
//
//
//    @Mock
//    StyleRepository styleRepository;
//
//    @Mock
//    MemberRepository memberRepository;
//
//    @Mock
//    CommentRepository commentRepository;
//
//    @InjectMocks
//    CommentService commentService;
//
//    @Test
//    void 댓글조회() {
//        // given
//        Long styleId = 1L;
//        Pageable pageable = PageRequest.of(0, 10);
//
//        // 가상의 Page<Comment> 객체 생성
//        Page<Comment> commentsPage = new PageImpl<>(Collections.emptyList());
//
//        // commentRepository.findByStyleId(styleId, pageable)가 호출될 때 가상의 Page<Comment> 반환 설정
//        when(commentRepository.findByStyleId(styleId, pageable)).thenReturn(commentsPage);
//
//        // when
//        // CommentService의 getAllCommentsByStyleId 메서드 호출
//        Page<Comment> result = commentService.getAllCommentsByStyleId(styleId, pageable);
//
//        // then
//        // commentRepository.findByStyleId(styleId, pageable) 메서드가 1번 호출되었는지 검증
//        verify(commentRepository, times(1)).findByStyleId(styleId, pageable);
//
//        // 결과가 올바르게 반환되었는지 검증
//        assertNotNull(result);
//        assertEquals(commentsPage, result);
//    }
//
//
//    @Test
//    void 댓글저장() {
//        //given
//        Long styleId = 1L;
//        Long memberId = 1L;
//        String content = "test content";
//
//        Style style = Style.builder()
//                .id(styleId)
//                .build();
//
//        Member member = Member.builder()
//                .id(memberId)
//                .build();
//
//        when(styleRepository.findById(styleId)).thenReturn(Optional.of(style));
//        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));
//
//        //when
//        commentService.save(styleId, content, memberId);
//
//        //then
//        verify(styleRepository, times(1)).findById(styleId);
//        verify(memberRepository, times(1)).findById(memberId);
//        verify(commentRepository, times(1)).save(any(Comment.class));
//    }
//
//
//    @Test
//    void 댓글수정() {
//        // given
//        Long commentId = 1L;
//        Long styleId = 2L;
//        Long memberId = 3L;
//        String updatedContent = "updated content";
//
//        Comment existingComment = Comment.builder()
//                .id(commentId)
//                .style(new Style())
//                .member(new Member())
//                .content("original content")
//                .build();
//
//        Style style = Style.builder()
//                .id(styleId)
//                .build();
//
//        Member member = Member.builder()
//                .id(memberId)
//                .build();
//
//        when(commentRepository.findById(commentId)).thenReturn(Optional.of(existingComment));
//        when(styleRepository.findById(styleId)).thenReturn(Optional.of(style));
//        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));
//
//        // when
//        assertDoesNotThrow(() -> commentService.update(commentId, updatedContent));
//
//        // then
//        verify(commentRepository, times(1)).findById(commentId);
//        verify(styleRepository, times(1)).findById(styleId);
//        verify(memberRepository, times(1)).findById(memberId);
//        verify(commentRepository, times(1)).save(any(Comment.class));
//    }
//
//
//    @Test
//    void 댓글삭제() {
//        // given
//        Long commentId = 1L;
//        Long styleId = 2L;
//
//        Comment existingComment = Comment.builder()
//                .id(commentId)
//                .style(new Style())
//                .member(new Member())
//                .content("original content")
//                .build();
//
//        Style style = Style.builder()
//                .id(styleId)
//                .build();
//
//        when(styleRepository.findById(styleId)).thenReturn(Optional.of(style));
//        when(commentRepository.findById(commentId)).thenReturn(Optional.of(existingComment));
//
//        // when
//        assertDoesNotThrow(() -> commentService.delete(commentId));
//
//        // then
//        verify(styleRepository, times(1)).findById(styleId);
//        verify(commentRepository, times(1)).findById(commentId);
//        verify(commentRepository, times(1)).deleteById(commentId);
//    }
//
//
//
//
//
//}