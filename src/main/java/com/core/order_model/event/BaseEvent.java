package com.core.order_model.event;

import com.core.order_common.util.IdGenerator;
import com.core.order_common.util.TraceContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.Instant;

/**
 * Standard Envelope for all Kafka Events across the ecosystem.
 * Serialized to JSON and stored in the Outbox payload column before publishing.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseEvent<T> implements Serializable {

    private String eventId;
    private String eventType;
    private String aggregateId;
    private String traceId;
    private Instant timestamp;
    private T payload;

    public static <T> BaseEvent<T> of(String eventType, String aggregateId, T payload) {
        return BaseEvent.<T>builder()
                .eventId(IdGenerator.nextEventId())
                .eventType(eventType)
                .aggregateId(aggregateId)
                .traceId(TraceContext.getTraceId())
                .timestamp(Instant.now())
                .payload(payload)
                .build();
    }
}
