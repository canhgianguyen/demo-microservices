package com.example.orderservicemq.exception;

import com.example.orderservicemq.locale.Translator;
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
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse err = new ErrorResponse(HttpStatus.NOT_FOUND.value(), Translator.toLocale("error.msg.record.not_found"), details);
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    // Duplicate
    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<?> handlerDuplicateRecordException(DuplicateRecordException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse err = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), Translator.toLocale("error.msg.record.duplicate"), details);
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
