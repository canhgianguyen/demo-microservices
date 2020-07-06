package com.example.paymentservice.utils;

import com.example.paymentservice.model.response.GeneralResponse;
import com.example.paymentservice.service.AuthService;
import com.example.paymentservice.service.RestTemplateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

@Service
public class ResponseUtils extends AuthService {
    @Autowired
    RestTemplateService restTemplateService;

    public GeneralResponse<Object> execute(HttpMethod httpMethod, String url, Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getLoggedUser().getToken());


        ObjectMapper mapper = new ObjectMapper();
        ResponseEntity<GeneralResponse> responseObject = restTemplateService
                .process(httpMethod,
                        url,
                        headers,
                        body,
                        GeneralResponse.class);
        return mapper.convertValue(responseObject.getBody(), GeneralResponse.class);
    }
}
