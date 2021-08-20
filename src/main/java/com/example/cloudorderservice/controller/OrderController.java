package com.example.cloudorderservice.controller;


import com.example.cloudorderservice.dto.OrderDto;
import com.example.cloudorderservice.jpa.OrderEntity;
import com.example.cloudorderservice.service.OrderService;
import com.example.cloudorderservice.vo.RequestOrder;
import com.example.cloudorderservice.vo.ResponseOrder;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order-service")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    Environment env;

    @GetMapping("/health_check")
    public String status() {
        return String.format("ok %s",env.getProperty("local.server.port"));
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createPrder(@PathVariable("userId") String userId, @RequestBody RequestOrder order) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        OrderDto orderDto = mapper.map(order, OrderDto.class);
        orderDto.setUserId(userId);

        OrderDto created = orderService.createOrder(orderDto);

        ResponseOrder responseOrder = mapper.map(created, ResponseOrder.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrders(@PathVariable("userId") String userId) {

        Iterable<OrderEntity> orderList = orderService.getAllOrderByUserId(userId);

        List<ResponseOrder> result = new ArrayList<>();
        orderList.forEach(v -> {
            result.add(new ModelMapper().map(v, ResponseOrder.class));
        });
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

//    @GetMapping("/orders")
//    public ResponseEntity<List<ResponseOrder>> getOrdersByUserId() {
//
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }
}
