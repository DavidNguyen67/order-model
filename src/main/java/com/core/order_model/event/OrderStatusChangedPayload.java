package com.core.order_model.event;

import com.core.order_model.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusChangedPayload implements Serializable {
    private String orderNumber;
    private OrderStatus oldStatus;
    private OrderStatus newStatus;
    private String reason;
}
