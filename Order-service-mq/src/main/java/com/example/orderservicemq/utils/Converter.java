package com.example.orderservicemq.utils;

import com.example.orderservicemq.entity.Order;
import com.example.orderservicemq.entity.OrderDetail;
import com.example.orderservicemq.model.response.OrderDetailResponse;
import com.example.orderservicemq.model.response.OrderResponse;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    public static OrderDetailResponse orderDetailToOrderDetailResponse(OrderDetail orderDetail) {
        return OrderDetailResponse.builder()
                .id(orderDetail.getId())
                .orderId(orderDetail.getOrder().getId())
                .breadId(orderDetail.getBread().getId())
                .amount(orderDetail.getAmount())
                .total(orderDetail.getTotal())
                .build();
    }

    public static OrderResponse orderToOrderResponse(Order order) {
        List<OrderDetail> orderDetailList = order.getOrderDetails();
        List<OrderDetailResponse> orderDetailResponseList = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetailList) {
            orderDetailResponseList.add(Converter.orderDetailToOrderDetailResponse(orderDetail));
        }
        return OrderResponse.builder()
                .id(order.getId())
                .address(order.getAddress())
                .number(order.getNumber())
                .orderStatus(order.getOrderStatus())
                .orderDetailResponses(orderDetailResponseList)
                .total(order.getTotal())
                .build();
    }
}
