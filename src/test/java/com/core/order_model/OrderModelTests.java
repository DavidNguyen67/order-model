package com.core.order_model;

import com.core.order_model.document.OrderEventDocument;
import com.core.order_model.entity.OrderEntity;
import com.core.order_model.entity.OrderStatus;
import com.core.order_model.event.BaseEvent;
import com.core.order_model.event.OrderCreatedPayload;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OrderModelTests {

    @Test
    void testOrderEntityCreation() {
        OrderEntity entity = OrderEntity.builder()
                .orderNumber("ORD-TEST-001")
                .customerId("CUST-100")
                .totalAmount(BigDecimal.valueOf(150.0))
                .status(OrderStatus.CREATED)
                .version(0L)
                .build();

        assertEquals("ORD-TEST-001", entity.getOrderNumber());
        assertEquals(OrderStatus.CREATED, entity.getStatus());
        assertEquals(0L, entity.getVersion());
    }

    @Test
    void testBaseEventCreation() {
        OrderCreatedPayload payload = OrderCreatedPayload.builder()
                .orderNumber("ORD-100")
                .customerId("CUST-1")
                .totalAmount(BigDecimal.valueOf(200))
                .build();

        BaseEvent<OrderCreatedPayload> event = BaseEvent.of("ORDER_CREATED", "ORD-100", payload);
        assertNotNull(event.getEventId());
        assertEquals("ORDER_CREATED", event.getEventType());
        assertEquals("ORD-100", event.getAggregateId());
        assertEquals(payload, event.getPayload());
    }
}
