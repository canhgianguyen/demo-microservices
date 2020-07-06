package com.example.orderservicemq.service.impl;

import com.example.orderservicemq.entity.Bread;
import com.example.orderservicemq.entity.Order;
import com.example.orderservicemq.entity.OrderDetail;
import com.example.orderservicemq.exception.NotFoundException;
import com.example.orderservicemq.locale.Translator;
import com.example.orderservicemq.model.dto.OrderDetailDto;
import com.example.orderservicemq.model.dto.OrderDto;
import com.example.orderservicemq.model.response.OrderResponse;
import com.example.orderservicemq.model.type.OrderStatusType;
import com.example.orderservicemq.repository.BreadRepository;
import com.example.orderservicemq.repository.OrderDetailRepository;
import com.example.orderservicemq.repository.OrderRepository;
import com.example.orderservicemq.service.OrderService;
import com.example.orderservicemq.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {
    @Autowired
    private BreadRepository breadRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Transactional(rollbackFor = SQLException.class)
    @Override
    public OrderResponse createOrder(OrderDto orderDto) {
        Order order = Order.builder()
                .address(orderDto.getAddress())
                .number(orderDto.getNumber())
                .orderStatus(OrderStatusType.WAITING)
                .build();

        List<OrderDetail> orderDetailList = new ArrayList<>();
        List<OrderDetailDto> orderDetailDtoList = orderDto.getOrderDetails();

        BigDecimal total = new BigDecimal(0);

        // Get bread from list orderDetailDto
        for (OrderDetailDto orderDetailDto : orderDetailDtoList) {
            Bread bread = breadRepository
                    .findByType(orderDetailDto.getType())
                    .orElseThrow(() -> new NotFoundException(
                            Translator.toLocale("error.msg.record.not_found_detail") + orderDetailDto.getType()));
            OrderDetail orderDetail = OrderDetail.builder()
                    .order(order)
                    .bread(bread)
                    .amount(orderDetailDto.getAmount())
                    .total(bread.getPrice().multiply(BigDecimal.valueOf(orderDetailDto.getAmount())))
                    .build();

            total = total.add(orderDetail.getTotal());

            // add orderDetail to db
            orderDetailList.add(orderDetailRepository.save(orderDetail));
        }

        order.setTotal(total);
        order.setOrderDetails(orderDetailList);
        return Converter.orderToOrderResponse(orderRepository.save(order));
    }
}
