package com.core.order_model.document;

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
 * MongoDB Distributed Lock document to handle race conditions across consumer instances.
 */
@Document(collection = "distributed_locks")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MongoLockDocument {

    @Id
    private String lockKey;

    private String lockedBy;

    private Instant lockedAt;

    /**
     * Automatic TTL expiration to prevent deadlocks if the acquiring process crashes.
     */
    @Indexed(expireAfter = "0s")
    private Instant expireAt;
}
