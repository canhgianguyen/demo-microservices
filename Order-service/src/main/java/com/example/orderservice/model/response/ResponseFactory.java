package com.example.orderservice.model.response;

import com.example.orderservice.loccale.Translator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseFactory {
    public ResponseEntity<?> success(Object data) {
        GeneralResponse<Object> responseObject = new GeneralResponse<>();
        ResponseStatus responseStatus = new ResponseStatus(HttpStatus.OK.toString(), Translator.toLocale("msg.success"));
        responseObject.setStatus(responseStatus);
        responseObject.setData(data);
        return ResponseEntity.ok(responseObject);
    }
}
