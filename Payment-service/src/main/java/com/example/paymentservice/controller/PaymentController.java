package com.example.paymentservice.controller;

import com.example.paymentservice.model.response.ResponseFactory;
import com.example.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pay/")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @Autowired
    private ResponseFactory responseFactory;

    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> pay(@PathVariable Long orderId) {
        return responseFactory.success(paymentService.pay(orderId));
    }
}
