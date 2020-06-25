package com.example.orderservice.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomPrincipal {
    private Integer id;
    private String username;
    private String token;
}
