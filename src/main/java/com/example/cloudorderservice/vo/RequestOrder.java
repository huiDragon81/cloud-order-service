package com.example.cloudorderservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestOrder {
    String productId;
    Integer qty;
    Integer unitPrice;
}
