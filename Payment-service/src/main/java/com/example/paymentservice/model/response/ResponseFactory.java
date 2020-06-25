package com.example.paymentservice.model.response;

import com.example.paymentservice.loccale.Translator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseFactory {
    public ResponseEntity<?> success(Object data) {
        GeneralResponse<Object> responseObject = new GeneralResponse<>();
        ResponseStatus responseStatus = new ResponseStatus(String.valueOf(HttpStatus.OK.value()), Translator.toLocale("msg.success"));
        responseObject.setStatus(responseStatus);
        responseObject.setData(data);
        return ResponseEntity.ok(responseObject);
    }
}
