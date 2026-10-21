package com.core.order_model.repository;

import com.core.order_model.document.MongoLockDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoLockRepository extends MongoRepository<MongoLockDocument, String> {
    Optional<MongoLockDocument> findByLockKey(String lockKey);
    void deleteByLockKey(String lockKey);
}
