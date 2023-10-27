package project.stylemate.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import project.stylemate.enums.ReturnCode;

@Getter
@RequiredArgsConstructor
public class SmException extends RuntimeException {
    private final ReturnCode returnCode;
}
