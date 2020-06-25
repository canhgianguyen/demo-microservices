package com.example.paymentservice.service;

import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.exception.OutOfBalanceException;
import com.example.paymentservice.exception.PaidException;
import com.example.paymentservice.loccale.Translator;
import com.example.paymentservice.model.dto.OrderDto;
import com.example.paymentservice.model.dto.UserDto;
import com.example.paymentservice.model.response.GeneralResponse;
import com.example.paymentservice.repository.PaymentRepository;
import com.example.paymentservice.utils.ResponseUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
public class PaymentService extends ResponseUtils {
    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    ResponseUtils responseUtils;

    @Value("${gateway.url}")
    String gatewayUrl;

    @Value("${order.url}")
    String orderUrl;

    @Value("${user.url}")
    String userUrl;

    /**
     *
     * @param orderId
     * @return
     */
    public Object pay(Long orderId) {
        ObjectMapper mapper = new ObjectMapper();

        // get order
        GeneralResponse<?> orderResponse = responseUtils.getGeneralResponse(
                HttpMethod.GET,
                gatewayUrl + orderUrl + orderId,
                null);
        OrderDto order = mapper.convertValue(orderResponse.getData(), OrderDto.class);

        // get user
        GeneralResponse<?> userResponse = responseUtils.getGeneralResponse(
                HttpMethod.GET,
                gatewayUrl + userUrl + getLoggedUser().getUsername(),
                null);
        UserDto user = mapper.convertValue(userResponse.getData(), UserDto.class);

        if (order.getStatus() == 1) {
            throw new PaidException(Translator.toLocale("error.msg.order.paid_detail"));
        }

        if (user.getBalance().compareTo(order.getTotal()) == -1) {
            throw new OutOfBalanceException(Translator.toLocale("error.msg.user.out_of_balance"));
        }

        user.setBalance(user.getBalance().subtract(order.getTotal()));
        responseUtils.getGeneralResponse(
                HttpMethod.PUT,
                gatewayUrl + userUrl,
                user
        );

        order.setStatus(1);
        responseUtils.getGeneralResponse(
                HttpMethod.PUT,
                gatewayUrl + orderUrl,
                order
        );

        Payment payment = Payment.builder()
                .orderId(order.getId())
                .userId(user.getId())
                .total(order.getTotal())
                .build();

        paymentRepository.save(payment);

        return "Successfully";
    }
}
