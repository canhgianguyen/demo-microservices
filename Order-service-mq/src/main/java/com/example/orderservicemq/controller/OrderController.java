package com.example.orderservicemq.controller;

import com.example.orderservicemq.model.dto.OrderDto;
import com.example.orderservicemq.model.response.ResponseFactory;
import com.example.orderservicemq.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ResponseFactory responseFactory;

    @PostMapping
    public ResponseEntity createOrder(@RequestBody OrderDto orderDto) {
        return responseFactory.success(orderService.createOrder(orderDto));
    }
}
