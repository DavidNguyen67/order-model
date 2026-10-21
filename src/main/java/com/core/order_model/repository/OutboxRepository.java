package com.core.order_model.repository;

import com.core.order_common.enums.OutboxStatus;
import com.core.order_model.entity.OutboxEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OutboxRepository extends JpaRepository<OutboxEntity, Long> {
    Optional<OutboxEntity> findByEventId(String eventId);

    List<OutboxEntity> findByStatusAndRetryCountLessThanOrderByCreatedAtAsc(
            OutboxStatus status,
            int maxRetry,
            Pageable pageable
    );

    List<OutboxEntity> findByStatusOrderByCreatedAtAsc(OutboxStatus status, Pageable pageable);
}
