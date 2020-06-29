package com.example.paymentservice.exception;

import com.example.paymentservice.loccale.Translator;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

public class RestTemplateResponseErrorHandler extends DefaultResponseErrorHandler {
    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        switch (clientHttpResponse.getStatusCode()) {
            case NOT_FOUND:
                throw new NotFoundException(Translator.toLocale("error.msg.record.not_found"));
            default:
                throw new BusinessException(Translator.toLocale("error.msg.internal_general_server_error"));
        }
    }
}
