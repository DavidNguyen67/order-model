package com.core.order_model.entity;

import com.core.order_common.enums.OutboxStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Transactional Outbox Entity stored in the SAME MySQL transaction as the domain entity.
 * Eliminates the Dual-Writer problem entirely: a relay (after-commit listener + Quartz
 * polling job) reads PENDING rows and publishes them to Kafka at-least-once.
 */
@Entity
@Table(name = "outbox_events", indexes = {
        @Index(name = "idx_outbox_status_retry", columnList = "status, retryCount"),
        @Index(name = "idx_outbox_event_id", columnList = "eventId", unique = true),
        @Index(name = "idx_outbox_created", columnList = "created_at")
})
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class OutboxEntity extends BaseEntity {

    @Column(nullable = false, unique = true, length = 64)
    private String eventId;

    @Column(nullable = false, length = 64)
    private String aggregateType;

    @Column(nullable = false, length = 64)
    private String aggregateId;

    @Column(nullable = false, length = 64)
    private String eventType;

    @Column(nullable = false, length = 128)
    private String topic;

    /**
     * Serialized JSON envelope (BaseEvent). Maps to LONGTEXT on MySQL.
     */
    @Lob
    @Column(nullable = false)
    private String payload;

    @Column(length = 64)
    private String traceId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private OutboxStatus status;

    @Builder.Default
    @Column(nullable = false)
    private int retryCount = 0;

    @Column(length = 1000)
    private String lastError;

    private LocalDateTime processedAt;
}
