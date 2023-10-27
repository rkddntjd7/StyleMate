package project.stylemate.exception;

import project.stylemate.enums.ReturnCode;

public class SmLogicException extends SmException {
    public SmLogicException(ReturnCode returnCode) {
        super(returnCode);
    }
}
