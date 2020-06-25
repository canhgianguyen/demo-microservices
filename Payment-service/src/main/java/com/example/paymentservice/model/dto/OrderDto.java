package com.example.paymentservice.model.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private Long userId;
    private Long shopId;
    private BigDecimal total;
    private int status;
}
