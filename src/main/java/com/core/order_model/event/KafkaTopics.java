package com.core.order_model.event;

/**
 * Central registry of Kafka topics and event types used by the Outbox relay and consumers.
 */
public final class KafkaTopics {

    private KafkaTopics() {}

    public static final String ORDER_EVENTS_TOPIC = "order-events";
    public static final String ORDER_EVENTS_DLT = "order-events.DLT";

    public static final String EVENT_TYPE_ORDER_CREATED = "ORDER_CREATED";
    public static final String EVENT_TYPE_ORDER_STATUS_CHANGED = "ORDER_STATUS_CHANGED";
}
