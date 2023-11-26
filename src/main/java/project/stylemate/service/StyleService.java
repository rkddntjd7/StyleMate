package project.stylemate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.stylemate.dto.style.StyleSearchCondition;
import project.stylemate.dto.params.UpsertStyleParam;
import project.stylemate.entity.Member;
import project.stylemate.entity.Style;
import project.stylemate.enums.ReturnCode;
import project.stylemate.exception.SmLogicException;
import project.stylemate.repository.MemberRepository;
import project.stylemate.repository.StyleRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Arrays;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
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
    public void save(UpsertStyleParam param) {
        Member member = memberRepository.findById(param.getMemberId())
                .orElseThrow(() -> new SmLogicException(ReturnCode.MEMBER_NOT_FOUND));

        Style style = param.toEntity(member);

        styleRepository.save(style);
    }

    @Transactional
    public void update(Long styleId, UpsertStyleParam param) {
        Style style = styleRepository.findById(styleId)
                .orElseThrow(() -> new SmLogicException(ReturnCode.STYLE_NOT_FOUND));

        style.update(
                param.getStyleImages(),
                param.getGender(),
                param.getMinHeight(),
                param.getMaxHeight(),
                param.getStyleCategory(),
                param.getContent(),
                param.getStyleRank()
        );
    }

    @Transactional
    public void delete(Long styleId) {
        Style style = styleRepository.findById(styleId)
                .orElseThrow(() -> new SmLogicException(ReturnCode.STYLE_NOT_FOUND));


        style.delete(LocalDateTime.now());

    }

    @Transactional
    public void increaseViews(Long styleId, HttpServletRequest request, HttpServletResponse response) {
        //TODO: redis 사용하여 어뷰징 방지
        Style style = styleRepository.findById(styleId)
                .orElseThrow(() -> new SmLogicException(ReturnCode.STYLE_NOT_FOUND));


        if (!hasViewed(styleId, request)) {
            style.increaseViews((int) style.getViewCount());
            setViewedCookie(styleId, response);
        } else {
            log.info("이미 조회한 게시물입니다.");
        }
    }

    private boolean hasViewed(Long styleId, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            return Arrays.stream(cookies)
                    .anyMatch(cookie -> cookie.getName().equals("viewed_style_" + styleId));
        }
        return false;
    }

    private void setViewedCookie(Long styleId, HttpServletResponse response) {
        Cookie viewedCookie = new Cookie("viewed_style_" + styleId, "true");
        viewedCookie.setMaxAge(24 * 60 * 60);
        viewedCookie.setPath("/");
        response.addCookie(viewedCookie);
    }

}
