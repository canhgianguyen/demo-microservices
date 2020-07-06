package com.example.orderservicemq.entity;

import com.example.orderservicemq.model.type.OrderStatusType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "number")
    private int number;

    @Column(name = "order_status")
    private OrderStatusType orderStatus;

    @Column(name = "total")
    private BigDecimal total;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;
}
