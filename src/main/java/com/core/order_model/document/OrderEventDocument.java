package com.core.order_model.document;

import com.core.order_common.enums.EventStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

/**
 * MongoDB Document for storing Kafka events (Event Sourcing / Event Log / Outbox pattern).
 */
@Document(collection = "order_events")
@CompoundIndex(name = "idx_agg_status", def = "{'aggregateId': 1, 'status': 1}")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEventDocument {

    @Id
    private String id;

    /**
     * Unique eventId to ensure idempotency and prevent duplicate event processing.
     */
    @Indexed(unique = true)
    private String eventId;

    private String eventType;

    @Indexed
    private String aggregateId;

    private String topic;

    private Integer partition;

    private Long offset;

    /**
     * Payload stored as serialized JSON string or structured object.
     */
    private String payload;

    @Indexed
    private EventStatus status;

    @Builder.Default
    private int retryCount = 0;

    private String lastError;

    private String traceId;

    @Builder.Default
    private Instant createdAt = Instant.now();

    private Instant processedAt;

    private Instant updatedAt;
}
