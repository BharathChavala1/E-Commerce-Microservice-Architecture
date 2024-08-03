package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "OrderService")
public class Order {
    @Id
    private Long id;
    private String orderNumber;
    @OneToMany(cascade = CascadeType.MERGE)
    List<OrderLineItem> orderLineItemList;
}
