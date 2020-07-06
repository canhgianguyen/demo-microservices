package com.example.orderservicemq.model.response;

import com.example.orderservicemq.model.type.OrderStatusType;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private Long id;
    private String address;
    private int number;
    private OrderStatusType orderStatus;
    private List<OrderDetailResponse> orderDetailResponses;
    private BigDecimal total;
}
