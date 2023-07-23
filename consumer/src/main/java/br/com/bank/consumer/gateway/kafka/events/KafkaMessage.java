package br.com.bank.consumer.gateway.kafka.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KafkaMessage {
    private String event;
    private EventSourceProducer source;
}
