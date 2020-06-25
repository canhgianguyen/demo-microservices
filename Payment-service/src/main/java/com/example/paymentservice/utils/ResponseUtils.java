package com.example.paymentservice.utils;

import com.example.paymentservice.exception.NotFoundException;
import com.example.paymentservice.loccale.Translator;
import com.example.paymentservice.model.response.GeneralResponse;
import com.example.paymentservice.service.AuthService;
import com.example.paymentservice.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponseUtils extends AuthService {
    @Autowired
    RestTemplateService restTemplateService;

    public GeneralResponse<Object> getGeneralResponse(HttpMethod httpMethod, String url, Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getLoggedUser().getToken());

        try {
            ResponseEntity<GeneralResponse<Object>> responseObject = restTemplateService
                    .process(httpMethod,
                            url,
                            headers,
                            body,
                            GeneralResponse.class);
            GeneralResponse<Object> generalResponse = responseObject.getBody();
            return generalResponse;
        } catch (Exception e) {
            throw new NotFoundException(Translator.toLocale("error.msg.record.not_found"));
        }
    }
}
