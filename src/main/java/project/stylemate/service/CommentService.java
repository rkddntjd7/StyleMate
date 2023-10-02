package project.stylemate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.stylemate.entity.Comment;
import project.stylemate.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    //댓글 전부 조회
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    //id로 댓글 조회
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));
    }

    //댓글 등록
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    //댓글 수정
    public Comment updateComment(Long id, Comment updateComment) {
        Comment findComment = getCommentById(id);

        findComment.setContent(updateComment.getContent());

        return commentRepository.save(findComment);
    }

    //댓글 삭제
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
