package com.example.paymentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class OutOfBalanceException extends RuntimeException{
    public OutOfBalanceException(String message) {
        super(message);
    }
}
