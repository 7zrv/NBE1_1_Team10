package org.example.gc_coffee.global.common.response;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ExceptionResponse<T> implements ResponseBase{

    private int code;
    private String message;

    public static ExceptionResponse<?> of(int code, String message) {
        return new ExceptionResponse<>(code, message);
    }

    public ExceptionResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

}