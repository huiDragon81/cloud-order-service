package com.example.cloudorderservice.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDto implements Serializable {

    String productId;

    Integer qty;
    Integer unitPrice;
    Integer totalPrice;

    String orderId;
    String userId;
}
