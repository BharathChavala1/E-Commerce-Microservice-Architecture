package com.example.demo.service;

import com.example.demo.entity.InventoryResponse;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderLineItem;
import com.example.demo.orderDTO.OrderLineItemsDTO;
import com.example.demo.orderDTO.OrderPlacedEvent;
import com.example.demo.orderDTO.OrderRequest;
import com.example.demo.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional
@Slf4j
public class OrderService {

    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    private  KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;

    @Autowired
    private  WebClient webclient;
    @Autowired
    public EntityManager entityManager;

    @Transactional
    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setId(orderRequest.getId());
        order.setOrderNumber(orderRequest.getOrderNumber());
        List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItemsListDTO().stream().map(this::mapToOrderLineItemList).toList();
        order.setOrderLineItemList(orderLineItems);
        List<String> skuCode = order.getOrderLineItemList().stream().map(OrderLineItem::getSkuCode).toList();
        InventoryResponse[] inventoryResponse = webclient.get().uri("http://inventory-service:8021/inventory/",
                      uriBuilder -> uriBuilder
                              .queryParam("skuCode", skuCode)
                              .build())
              .retrieve()
              .bodyToMono(InventoryResponse[].class)
              .block();
      //inventory response array in Stock all should be true to place an order
        boolean results = Arrays.stream(inventoryResponse).allMatch(InventoryResponse::isInStock);
        if (results){
            orderRepository.save(order);
         kafkaTemplate.send("notificationTopic",new OrderPlacedEvent(order.getOrderNumber()));
            return "Ordered Successfully";
        }
        else {
            throw new IllegalArgumentException("Product is not in Stock Please try again later");
        }
    }


    public OrderLineItem mapToOrderLineItemList(OrderLineItemsDTO orderLineItemsDTO) {
        return OrderLineItem.builder()
                .id(orderLineItemsDTO.getId())
                .skuCode(orderLineItemsDTO.getSkuCode())
                .quatity(orderLineItemsDTO.getQuantity())
                .price(orderLineItemsDTO.getPrice())
                .build();
    }
}
