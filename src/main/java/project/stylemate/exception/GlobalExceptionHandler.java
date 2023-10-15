package project.stylemate.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import project.stylemate.dto.common.ApiResponse;
import project.stylemate.enums.ReturnCode;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SmException.class)
    public ApiResponse<?> handleSmException(SmException e) {
        return ApiResponse.of(e.getReturnCode());
    }

    @ExceptionHandler(value = {
        MethodArgumentTypeMismatchException.class
    })
    public ApiResponse<?> handleRequestException(Exception e) {
        return ApiResponse.of(ReturnCode.WRONG_PARAMETER);
    }


}
