package com.core.order_model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * JPA Entity for Orders stored in MySQL.
 * Inherits optimistic locking (@Version) from BaseEntity to prevent race conditions.
 */
@Entity
@Table(name = "orders", indexes = {
        @Index(name = "idx_order_number", columnList = "orderNumber", unique = true),
        @Index(name = "idx_customer_id", columnList = "customerId")
})
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class OrderEntity extends BaseEntity {

    @Column(nullable = false, unique = true, length = 64)
    private String orderNumber;

    @Column(nullable = false, length = 64)
    private String customerId;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private OrderStatus status;

    @Column(length = 512)
    private String notes;
}
