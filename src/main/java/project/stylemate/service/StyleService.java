package project.stylemate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.stylemate.dto.style.StyleSearchCondition;
import project.stylemate.dto.params.SaveUpdateStyleParam;
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
import java.time.temporal.ChronoUnit;

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
    public void save(SaveUpdateStyleParam param) {
        Member member = memberRepository.findById(param.getMemberId())
                .orElseThrow(() -> new SmLogicException(ReturnCode.MEMBER_NOT_FOUND));

        Style style = param.toEntity(member);

        styleRepository.save(style);
    }

    @Transactional
    public void update(Long styleId, SaveUpdateStyleParam param) {
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
    public void increaseViews(Long styleId) {
        Style style = styleRepository.findById(styleId)
                .orElseThrow(() -> new SmLogicException(ReturnCode.STYLE_NOT_FOUND));

        style.increaseViews((int) style.getViewCount());
    }

//    @Transactional
//    public void increaseViews(Long styleId, HttpServletRequest request, HttpServletResponse response) {
//        Style style = styleRepository.findById(styleId)
//                .orElseThrow(() -> new SmLogicException(ReturnCode.STYLE_NOT_FOUND));
//
//        Cookie oldCookie = null;
//
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("styleView")) {
//                    oldCookie = cookie;
//                }
//            }
//        }
//
//        if (oldCookie != null) {
//            if (!oldCookie.getValue().contains("[" + styleId.toString() + "]")) {
//                style.increaseViews((int) style.getViewCount());
//                oldCookie.setValue(oldCookie.getValue() + "_[" + styleId + "]");
//                oldCookie.setPath("/");
//                oldCookie.setMaxAge(60 * 60 * 24);
//                response.addCookie(oldCookie);
//            }
//        } else {
//            style.increaseViews((int) style.getViewCount());
//            Cookie newCookie = new Cookie("styleView", "[" + styleId + "]");
//            newCookie.setPath("/");
//            oldCookie.setMaxAge(60 * 60 * 24);
//            response.addCookie(newCookie);
//        }
//    }
}
