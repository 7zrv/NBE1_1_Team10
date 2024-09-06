package org.example.gc_coffee.global.exception;


import com.yoon.dailydevelop.global.common.response.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse<?> handleBadRequestException(final BadRequestException e) {

        log.warn(e.getMessage(), e);

        return ExceptionResponse.of(e.getCode(), e.getMessage());
    }


    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse<?> handleDuplicateException(final DuplicateException e) {

        log.warn(e.getMessage(), e);

        return ExceptionResponse.of(e.getCode(), e.getMessage());
    }




}
