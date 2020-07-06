package com.example.orderservicemq.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private String address;
    private int number;
    private List<OrderDetailDto> orderDetails;
}
