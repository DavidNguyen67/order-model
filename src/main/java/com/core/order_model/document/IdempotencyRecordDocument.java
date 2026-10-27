package com.core.order_model.document;

import com.core.order_common.enums.IdempotencyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

/**
 * MongoDB Document for storing idempotency keys and cached results.
 * Employs a TTL index on expireAt for automatic eviction.
 */
@Document(collection = "idempotency_records")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdempotencyRecordDocument {

    @Id
    private String id;

    /**
     * Unique business key (e.g., hash of request or client-sent idempotency key or eventId).
     */
    @Indexed(unique = true)
    private String idempotencyKey;

    @Indexed
    private String aggregateId;

    @Indexed
    private IdempotencyStatus status;

    private String responsePayload;

    private String errorMessage;

    private String traceId;

    @Builder.Default
    private Instant createdAt = Instant.now();

    private Instant updatedAt;

    /**
     * Automatic TTL expiration managed by MongoDB.
     * expireAfter = 0s means the document is evicted exactly AT the expireAt instant;
     * the TTL duration itself is decided by whoever computes expireAt.
     */
    @Indexed(expireAfter = "0s")
    private Instant expireAt;
}
