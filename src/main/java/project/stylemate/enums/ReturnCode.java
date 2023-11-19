package project.stylemate.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReturnCode {
    //1400 - Member 클라이언트 에러
    //1500 - Member 서버 에러

    //2400 - Style 클라이언트 에러
    //2500 - Style 서버 에러

    //3400 - Comment 클라이언트 에러
    //3500 - Comment 서버 에러

    //4400 - Like 클라이언트 에러
    //4500 - Like 서버 에러
    SUCCESS("0000", "요청에 성공하였습니다."),

    WRONG_PARAMETER("1000", "잘못된 파라미터입니다."),
    WRONG_HTTP_METHOD("1001", "잘못된 요청메서드입니다."),
    CONSTRAINT_VIOLATION("1002","제약조건이 맞지 않습니다."),
    VALIDATION_ERROR("1004", "검증 과정에서 문제가 발생하였습니다."),
    MISSING_PARAMETER("1003", "필수 요청 매개변수가 누락되었습니다."),


    USERNAME_ALREADY_EXISTS("1400", "이미 사용중인 아이디입니다."),
    INVALID_GENDER("1401", "잘못된 성별 값입니다."),
    EXPIRED_OR_INVALID_VERIFICATION_CODE("1402", "인증번호가 유효하지 않거나 만료되었습니다. 새 코드를 요청하세요."),
    INVALID_PARAMETER_VALUE("1403", "잘못된 파라미터 값입니다."),
    INVALID_USERNAME_OR_PASSWORD("1404", "사용자 이름과 비밀번호를 확인하세요."),
    MEMBER_NOT_FOUND("1501", "해당 회원을 찾을 수 없습니다."),

    INVALID_STYLE_CATEGORY("2401", "잘못된 스타일 카테고리 값입니다."),
    INVALID_KEY("2402", "잘못된 신장 값입니다."),
    STYLE_NOT_FOUND("2501", "해당 스타일을 찾을 수 없습니다."),

    COMMENT_NOT_FOUND("3501", "해당 댓글을 찾을 수 없습니다."),
    COMMENT_NOT_IN_STYLE("3502","해당 댓글이 지정된 스타일에서 찾을 수 없습니다."),

    LIKE_NOT_FOUND("4501", "해당 좋아요를 찾을 수 없습니다.");


    private final String returnCode;
    private final String returnMessage;
}
