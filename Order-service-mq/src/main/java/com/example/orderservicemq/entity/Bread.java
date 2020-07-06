package com.example.orderservicemq.entity;

import com.example.orderservicemq.model.type.BreadType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "bread")
public class Bread {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type")
    private BreadType type;

    @Column(name = "price")
    private BigDecimal price;
}
