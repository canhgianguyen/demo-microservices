package com.example.orderservice.controller;

import com.example.orderservice.entity.Order;
import com.example.orderservice.exception.NotFoundException;
import com.example.orderservice.model.response.ResponseFactory;
import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    private ResponseFactory responseFactory;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        return responseFactory.success(orderService.getOrderById(id));
    }
}
