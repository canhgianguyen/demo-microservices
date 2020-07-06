package com.example.orderservicemq.service;

import com.example.orderservicemq.model.dto.OrderDto;
import com.example.orderservicemq.model.response.OrderResponse;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    OrderResponse createOrder(OrderDto orderDto);
}
