package org.example.gc_coffee.global.exception;



import lombok.extern.slf4j.Slf4j;
import org.example.gc_coffee.global.common.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler{


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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse<?> handleValidateException(final MethodArgumentNotValidException e) {

        log.warn(e.getMessage(), e);

        final String errMessage = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();

        return ExceptionResponse.of(400, errMessage);
    }




}
