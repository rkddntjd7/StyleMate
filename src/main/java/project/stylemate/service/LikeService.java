package project.stylemate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.stylemate.entity.Like;
import project.stylemate.entity.Member;
import project.stylemate.entity.Style;
import project.stylemate.enums.ReturnCode;
import project.stylemate.exception.SmLogicException;
import project.stylemate.repository.LikeRepository;
import project.stylemate.repository.MemberRepository;
import project.stylemate.repository.StyleRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final StyleRepository styleRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void addLike(Long memberId, Long styleId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new SmLogicException(ReturnCode.MEMBER_NOT_FOUND));

        Style style = styleRepository.findById(styleId)
                .orElseThrow(() -> new SmLogicException(ReturnCode.STYLE_NOT_FOUND));

        if (isNotAlreadyLike(member, style)) {
            likeRepository.save(Like.builder()
                    .member(member)
                    .style(style)
                    .liked(true)
                    .build()
            );
        }

    }

    private boolean isNotAlreadyLike(Member member, Style style) {
        return likeRepository.findByMemberAndStyle(member, style).isEmpty();
    }

    @Transactional
    public void delete(Long likeId) {
        Like like = likeRepository.findById(likeId)
                .orElseThrow(() -> new SmLogicException(ReturnCode.LIKE_NOT_FOUND));

        likeRepository.delete(like);
    }

    public Long getLikeCountByStyleId(Long styleId) {
        styleRepository.findById(styleId)
                .orElseThrow(() -> new SmLogicException(ReturnCode.STYLE_NOT_FOUND));

        return likeRepository.countByStyleId(styleId);
    }
}
