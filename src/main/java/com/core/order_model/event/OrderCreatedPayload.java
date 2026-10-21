package com.core.order_model.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedPayload implements Serializable {
    private String orderNumber;
    private String customerId;
    private BigDecimal totalAmount;
    private String notes;
}
