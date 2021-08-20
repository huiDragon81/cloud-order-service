package com.example.cloudorderservice.service;


import com.example.cloudorderservice.dto.OrderDto;
import com.example.cloudorderservice.jpa.OrderEntity;
import com.example.cloudorderservice.jpa.OrderRepository;
import com.example.cloudorderservice.vo.ResponseOrder;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public OrderDto createOrder(OrderDto orderDetails) {
        orderDetails.setOrderId(UUID.randomUUID().toString());
        orderDetails.setTotalPrice(orderDetails.getQty() * orderDetails.getUnitPrice());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        OrderEntity orderEntity = mapper.map(orderDetails, OrderEntity.class);
        orderRepository.save(orderEntity);

        OrderDto returnOrderDto = mapper.map(orderDetails, OrderDto.class);
        return returnOrderDto;
    }

    @Override
    public OrderDto getOrderByOrderId(String orderId) {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        OrderDto orderDto = new ModelMapper().map(orderEntity, OrderDto.class);
        return orderDto;
    }

    @Override
    public Iterable<OrderEntity> getAllOrderByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }
}
