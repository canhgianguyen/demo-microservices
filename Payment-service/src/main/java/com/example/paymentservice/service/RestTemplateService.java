package com.example.paymentservice.service;

import com.example.paymentservice.constant.CommonConstant;
import com.example.paymentservice.exception.BusinessException;
import com.example.paymentservice.exception.CustomException;
import com.example.paymentservice.exception.ResponseMessage;
import com.example.paymentservice.utils.ParserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class RestTemplateService {
    public static final String X_REQUEST_ID = "X-Request-Id";
    private final RestTemplate mrestTemplate;

    @Autowired
    public RestTemplateService(RestTemplate prestTemplate) {
        this.mrestTemplate = prestTemplate;
    }

    public <T> ResponseEntity process(HttpMethod phttpMethod, String pstrURL,
                                      HttpHeaders phttpHeaders,
                                      Object prequestBody, Class<T> presponseType) {
        if (phttpHeaders == null) {
            phttpHeaders = new HttpHeaders();
        }
        phttpHeaders.set(X_REQUEST_ID, UUID.randomUUID().toString());
        phttpHeaders.set(CommonConstant.TIMESTAMP,
                ZonedDateTime.now()
                        .format(DateTimeFormatter.ofPattern(CommonConstant.TIMESTAMP_DATE_PATTERN)));
        try {
            ResponseEntity vresponseEntity = mrestTemplate
                    .exchange(pstrURL, phttpMethod, new HttpEntity<>(prequestBody, phttpHeaders),
                            presponseType);
            HttpStatus vhttpStatus = vresponseEntity.getStatusCode();

            return vresponseEntity;

        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            throw new BusinessException(String.valueOf(HttpStatus.NO_CONTENT.value()),
                    ex.getMessage());

        } catch (ResourceAccessException ex) {
            if (ex.getCause() instanceof CustomException) {
                String responseMessageStr = ex.getCause().getMessage();
                ResponseMessage responseMessage = ParserUtils
                        .fromJson(responseMessageStr, ResponseMessage.class);
                throw new BusinessException(responseMessage.getStatus().getCode(),
                        responseMessage.getStatus().getMessage());
            }
            throw new BusinessException(String.valueOf(HttpStatus.BAD_REQUEST.value()),
                    ex.getMessage());
        }
    }
}
