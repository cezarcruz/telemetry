package br.com.bank.consumer.gateway.kafka.events;

public interface EventSourceProducer {
    String getTopicName();
    String getEvent();
}
