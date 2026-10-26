package com.core.order_model.repository;

import com.core.order_common.enums.EventStatus;
import com.core.order_model.document.EventRecordDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Generic MongoDB repository for Event Sourcing and Event Store audit trail.
 */
@Repository
public interface EventStoreMongoRepository extends MongoRepository<EventRecordDocument, String> {

    Optional<EventRecordDocument> findByEventId(String eventId);

    List<EventRecordDocument> findByAggregateIdOrderByCreatedAtDesc(String aggregateId);

    List<EventRecordDocument> findByStatus(EventStatus status);
}
