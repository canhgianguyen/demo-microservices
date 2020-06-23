package com.example.orderservice.service;

import com.example.orderservice.entity.Order;
import com.example.orderservice.exception.NotFoundException;
import com.example.orderservice.loccale.Translator;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public Order getOrderById(Long id) {
       return orderRepository.findById(id).orElseThrow(() -> new NotFoundException(Translator.toLocale("error.msg.record.not_found")));
    }
}
