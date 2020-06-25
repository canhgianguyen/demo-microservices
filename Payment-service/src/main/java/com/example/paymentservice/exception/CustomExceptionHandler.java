package com.example.paymentservice.exception;

import com.example.paymentservice.loccale.Translator;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler implements ResponseErrorHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server Error", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Not found record
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handlerNotFoundException(NotFoundException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse err = new ErrorResponse(HttpStatus.NOT_FOUND.value(), Translator.toLocale("error.msg.record.not_found"), details);
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    // Out of balance
    @ExceptionHandler(OutOfBalanceException.class)
    public ResponseEntity<?> handlerOutOfBalanceException(OutOfBalanceException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse err = new ErrorResponse(HttpStatus.NOT_ACCEPTABLE.value(), Translator.toLocale("error.msg.user.oob"), details);
        return new ResponseEntity<>(err, HttpStatus.NOT_ACCEPTABLE);
    }

    // paid order
    @ExceptionHandler(PaidException.class)
    public ResponseEntity<?> handlerPaidException(PaidException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse err = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), Translator.toLocale("error.msg.order.paid"), details);
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server Error", details);
        return ResponseEntity.ok(error);
    }

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        HttpStatus status = clientHttpResponse.getStatusCode();
        return status.is4xxClientError() || status.is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        String responseAsString = toString(clientHttpResponse.getBody());
        throw new CustomException(responseAsString);
    }

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        String responseAsString = toString(response.getBody());
        throw new CustomException(responseAsString);
    }

    String toString(InputStream inputStream) {
        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
