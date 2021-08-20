package com.example.cloudorderservice.service;


import com.example.cloudorderservice.dto.OrderDto;
import com.example.cloudorderservice.jpa.OrderEntity;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDetails);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<OrderEntity> getAllOrderByUserId(String userId);

}
