package com.core.order_model.document;

import com.core.order_common.enums.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

/**
 * Generic Event Store Document in MongoDB for Event Sourcing, audit logging,
 * and monitoring EDA workflows across ANY business domain.
 */
@Document(collection = "event_store")
@CompoundIndex(name = "idx_agg_status", def = "{'aggregateId': 1, 'status': 1}")
@CompoundIndex(name = "idx_status_created", def = "{'status': 1, 'createdAt': 1}")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventRecordDocument {

    @Id
    private String id;

    /**
     * Unique eventId to ensure idempotency and track lifecycle.
     */
    @Indexed(unique = true)
    private String eventId;

    private String eventType;

    @Indexed
    private String aggregateId;

    private String aggregateType;

    private String topic;

    private Integer partition;

    private Long offset;

    /**
     * Payload stored as serialized JSON string.
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
