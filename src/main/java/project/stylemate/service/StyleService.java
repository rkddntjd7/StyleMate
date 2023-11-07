package project.stylemate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.stylemate.dto.style.StyleSearchCondition;
import project.stylemate.dto.params.StyleParam;
import project.stylemate.entity.Member;
import project.stylemate.entity.Style;
import project.stylemate.enums.ReturnCode;
import project.stylemate.exception.SmLogicException;
import project.stylemate.repository.MemberRepository;
import project.stylemate.repository.StyleRepository;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StyleService {

    private final StyleRepository styleRepository;
    private final MemberRepository memberRepository;

    public Page<Style> getAllStyles(Long memberId, StyleSearchCondition cond, Pageable pageable) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new SmLogicException(ReturnCode.MEMBER_NOT_FOUND));

        return styleRepository.getAllStyles(cond, pageable);
    }

    public Style getStyleById(Long styleId) {
        return styleRepository.findById(styleId)
                .orElseThrow(() -> new SmLogicException(ReturnCode.STYLE_NOT_FOUND));
    }

    @Transactional
    public void save(StyleParam param) {
        Member member = memberRepository.findById(param.getMemberId())
                .orElseThrow(() -> new SmLogicException(ReturnCode.MEMBER_NOT_FOUND));

        Style style = param.toEntity(member);

        styleRepository.save(style);
    }

    @Transactional
    public void update(Long styleId, StyleParam param) {
        Style style = styleRepository.findById(styleId)
                .orElseThrow(() -> new SmLogicException(ReturnCode.STYLE_NOT_FOUND));

        style.updateStyle(
                param.getStyleImages(),
                param.getGender(),
                param.getMinHeight(),
                param.getMaxHeight(),
                param.getStyleCategory(),
                param.getContent(),
                param.getViewCount(),
                param.getStyleRank()
        );
    }

    @Transactional
    public void delete(Long styleId) {
        Style style = styleRepository.findById(styleId)
                .orElseThrow(() -> new SmLogicException(ReturnCode.STYLE_NOT_FOUND));


        style.deleteStyle(LocalDateTime.now());

    }

    @Transactional
    public void increaseViews(Long styleId) {
        Style style = styleRepository.findById(styleId)
                .orElseThrow(() -> new SmLogicException(ReturnCode.STYLE_NOT_FOUND));

        style.increaseViews(style.getViewCount());
    }
}
