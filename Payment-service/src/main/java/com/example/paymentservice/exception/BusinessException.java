package com.example.paymentservice.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class BusinessException extends RuntimeException {
    private String errorCode;
    private String message;

    public BusinessException(String msg) {
        super(msg);
        this.message = msg;
    }

    public BusinessException(String msg, Throwable ex) {
        super(msg, ex);
        this.message = msg;
    }

    public BusinessException(String errorCode, String msg, Throwable ex) {
        super(msg, ex);
        this.errorCode = errorCode;
        this.message = msg;
    }

    public BusinessException(String errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
        this.message = msg;
    }
}
