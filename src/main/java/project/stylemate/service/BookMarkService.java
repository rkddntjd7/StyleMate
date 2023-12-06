package project.stylemate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.stylemate.entity.BookMark;
import project.stylemate.entity.Member;
import project.stylemate.entity.Style;
import project.stylemate.enums.ReturnCode;
import project.stylemate.exception.SmLogicException;
import project.stylemate.repository.BookMarkRepository;
import project.stylemate.repository.MemberRepository;
import project.stylemate.repository.StyleRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookMarkService {

    private final BookMarkRepository bookMarkRepository;
    private final MemberRepository memberRepository;
    private final StyleRepository styleRepository;

    @Transactional
    public void add(Long memberId, Long styleId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new SmLogicException(ReturnCode.MEMBER_NOT_FOUND));

        Style style = styleRepository.findById(styleId)
                .orElseThrow(() -> new SmLogicException(ReturnCode.STYLE_NOT_FOUND));

        if (isNotAlreadyBookMark(member, style)) {
            bookMarkRepository.save(BookMark.builder()
                    .member(member)
                    .style(style)
                    .build()
            );

        }

    }

    public boolean isNotAlreadyBookMark(Member member, Style style) {
        return bookMarkRepository.findByMemberAndStyle(member, style).isEmpty();
    }

    @Transactional
    public void delete(Long bookMarkId) {
        BookMark bookMark = bookMarkRepository.findById(bookMarkId)
                .orElseThrow(() -> new SmLogicException(ReturnCode.BOOKMARK_NOT_FOUND));

        bookMarkRepository.delete(bookMark);
    }

    public boolean isBookMarkedByMember(Long memberId, Long styleId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new SmLogicException(ReturnCode.MEMBER_NOT_FOUND));

        Style style = styleRepository.findById(styleId)
                .orElseThrow(() -> new SmLogicException(ReturnCode.STYLE_NOT_FOUND));

        return bookMarkRepository.findByMemberAndStyle(member, style).isPresent();
    }
}
