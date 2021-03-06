package com.example.orderservice.exception;

import com.example.orderservice.locale.Translator;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Log4j2
public class CustomExceptionHandler {
    // Not found record
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handlerNotFoundException(NotFoundException ex, WebRequest request) {
        log.warn("Record not found.");
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse err = new ErrorResponse(HttpStatus.NOT_FOUND.value(), Translator.toLocale("error.msg.record.not_found"), details);
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
}
