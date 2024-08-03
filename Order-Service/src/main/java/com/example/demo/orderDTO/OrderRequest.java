package com.example.demo.orderDTO;

import com.example.demo.entity.OrderLineItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderRequest {
    private Long id;
    private String orderNumber;
    List<OrderLineItemsDTO> orderLineItemsListDTO;
}
