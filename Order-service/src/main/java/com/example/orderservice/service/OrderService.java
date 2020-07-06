package com.example.orderservice.service;

import com.example.orderservice.entity.Order;
import com.example.orderservice.exception.NotFoundException;
import com.example.orderservice.locale.Translator;
import com.example.orderservice.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public Order getOrderById(Long id) {
        log.info("Get order by id");
       return orderRepository
               .findById(id)
               .orElseThrow(() -> new NotFoundException(Translator.toLocale("error.msg.record.not_found")));
    }

    public Order updateOrder(Order order) {
        log.info("Update order");
        return orderRepository.findById(order.getId())
                .map((ord) -> {
                    ord.setStatus(order.getStatus());
                    return orderRepository.save(ord);
                })
                .orElseThrow(() -> new NotFoundException(Translator.toLocale("error.msg.record.not_found")));
    }
}
