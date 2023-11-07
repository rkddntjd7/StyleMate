package project.stylemate.exception;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import project.stylemate.dto.common.ApiResponse;
import project.stylemate.enums.ReturnCode;

import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SmException.class)
    public ApiResponse<?> handleSmException(SmException e) {
        return ApiResponse.of(e.getReturnCode());
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ApiResponse<?> handleMethodArgumentTypeMismatchException(Exception e) {
        return ApiResponse.of(ReturnCode.WRONG_PARAMETER);
    }

    @ExceptionHandler(value = {
            HttpMessageNotReadableException.class,
            HttpRequestMethodNotSupportedException.class
    })
    public ApiResponse<?> handleWrongHttpMethodException(Exception e) {
        return ApiResponse.of(ReturnCode.WRONG_HTTP_METHOD);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ApiResponse<?> handleConstraintViolationException(Exception e) {
        return ApiResponse.of(ReturnCode.CONSTRAINT_VIOLATION);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ApiResponse<?> handleMethodArgumentNotValidException(Exception e) {
        return ApiResponse.of(ReturnCode.VALIDATION_ERROR);
    }

    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public ApiResponse<?> handleMissingServletRequestParameterException(Exception e) {
        return ApiResponse.of(ReturnCode.MISSING_PARAMETER);
    }

    @ExceptionHandler(value = {SQLIntegrityConstraintViolationException.class})
    public ApiResponse<?> handleSQLIntegrityConstraintViolationException(Exception e) {
        return ApiResponse.of(ReturnCode.SQL_INTEGRITY_CONSTRAIN_VIOLATION);
    }

}
