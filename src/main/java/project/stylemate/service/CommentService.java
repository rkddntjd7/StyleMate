package project.stylemate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.stylemate.dto.params.SaveCommentParam;
import project.stylemate.entity.Comment;
import project.stylemate.entity.Member;
import project.stylemate.entity.Style;
import project.stylemate.enums.ReturnCode;
import project.stylemate.exception.SmException;
import project.stylemate.exception.SmLogicException;
import project.stylemate.repository.CommentRepository;
import project.stylemate.repository.MemberRepository;
import project.stylemate.repository.StyleRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;

    private final StyleRepository styleRepository;

    private final MemberRepository memberRepository;


    public Page<Comment> getAllCommentsByStyleId(Long styleId, Pageable pageable) {
        styleRepository.findById(styleId)
                .orElseThrow(() -> new SmLogicException(ReturnCode.STYLE_NOT_FOUND));

        return commentRepository.findByStyleId(styleId, pageable);
    }


    @Transactional
    public void save(Long styleId, String content, Long memberId) {
        Style style = styleRepository.findById(styleId)
                .orElseThrow(() -> new SmLogicException(ReturnCode.STYLE_NOT_FOUND));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new SmLogicException(ReturnCode.MEMBER_NOT_FOUND));

        Comment comment = Comment.builder()
                .member(member)
                .style(style)
                .content(content)
                .build();

        commentRepository.save(comment);
    }


    @Transactional
    public void update(Long commentId, String content) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new SmLogicException(ReturnCode.COMMENT_NOT_FOUND));

        comment.updateContent(content);

    }

    @Transactional
    public void delete(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new SmLogicException(ReturnCode.COMMENT_NOT_FOUND));

        commentRepository.delete(comment);
    }


}
