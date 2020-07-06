package com.example.orderservicemq.model.dto;

import com.example.orderservicemq.model.type.BreadType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailDto {
    private BreadType type;
    private int amount;
}
