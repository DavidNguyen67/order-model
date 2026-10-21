package com.core.order_model.repository;

import com.core.order_common.enums.EventStatus;
import com.core.order_model.document.OrderEventDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderEventMongoRepository extends MongoRepository<OrderEventDocument, String> {
    Optional<OrderEventDocument> findByEventId(String eventId);
    boolean existsByEventId(String eventId);
    List<OrderEventDocument> findByAggregateIdOrderByCreatedAtDesc(String aggregateId);
    List<OrderEventDocument> findByStatusAndRetryCountLessThan(EventStatus status, int maxRetry);
}
