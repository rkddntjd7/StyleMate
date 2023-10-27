package project.stylemate.exception;

import lombok.Getter;
import project.stylemate.enums.ReturnCode;

@Getter
public class SmRequestException extends SmException {
    public SmRequestException(ReturnCode returnCode) {
        super(returnCode);
    }
}
