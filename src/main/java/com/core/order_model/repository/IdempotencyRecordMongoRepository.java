package com.core.order_model.repository;

import com.core.order_model.document.IdempotencyRecordDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdempotencyRecordMongoRepository extends MongoRepository<IdempotencyRecordDocument, String> {
    Optional<IdempotencyRecordDocument> findByIdempotencyKey(String idempotencyKey);
    boolean existsByIdempotencyKey(String idempotencyKey);
    void deleteByIdempotencyKey(String idempotencyKey);
}
