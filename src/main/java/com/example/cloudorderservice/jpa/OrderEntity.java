package com.example.cloudorderservice.jpa;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true)
    String orderId;

    @Column(nullable = false)
    String userId;

    @Column(nullable = false, unique = true)
    String productId;

    @Column(nullable = false)
    Integer qty;

    @Column(nullable = false)
    Integer unitPrice;

    @Column(nullable = false)
    Integer totalPrice;

    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    Date createdAt;
}
