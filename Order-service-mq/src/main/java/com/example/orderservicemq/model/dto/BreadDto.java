package com.example.orderservicemq.model.dto;

import com.example.orderservicemq.model.type.BreadType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BreadDto {
    private BreadType type;
    private BigDecimal price;
}
