package com.example.demo.controller;

import com.example.demo.orderDTO.OrderLineItemsDTO;
import com.example.demo.orderDTO.OrderRequest;
import com.example.demo.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@RequestMapping("/order")
@Controller
public class OrderController {

    @Autowired
    public OrderService service;

    @ResponseBody
    @RequestMapping(value = "/placeOrder",method = RequestMethod.POST)
    @CircuitBreaker(name="inventory",fallbackMethod = "fallBackMethod")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest){
    String response =  service.placeOrder(orderRequest);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    public ResponseEntity<?> fallBackMethod(OrderRequest orderRequest,RuntimeException exception){
        return new ResponseEntity<>("Opps! Something went wrong,please try agian after sometime", HttpStatusCode.valueOf(org.apache.hc.core5.http.HttpStatus.SC_BAD_GATEWAY));
    }
}
