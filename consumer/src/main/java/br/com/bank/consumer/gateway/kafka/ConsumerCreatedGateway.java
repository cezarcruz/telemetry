package br.com.bank.consumer.gateway.kafka;

import br.com.bank.consumer.gateway.kafka.events.KafkaMessage;
import br.com.bank.consumer.gateway.kafka.events.MessageProducer;
import br.com.bank.consumer.gateway.kafka.factories.ConsumerEventSourceFactory;
import br.com.bank.consumer.gateway.mongo.entity.ConsumerEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerCreatedGateway {

    // temporaty
    //private final KafkaTemplate<String, ConsumerEntity> kafkaTemplate;
    private final StreamBridge streamBridge;
    private final ObjectMapper objectMapper;

    public void notify(final ConsumerEntity consumerEntity) {
        log.info("notifying consumer creation");

        final var event = ConsumerEventSourceFactory.from(consumerEntity);
        final var kafkaMessage = KafkaMessage.builder()
                .event(event.getEvent())
                .source(event)
                .build();

        final var source = kafkaMessage.getSource();

        try {
            final var message = objectMapper.writeValueAsString(source);

            streamBridge.send(event.getTopicName(), MessageProducer.toMessage(message, new MessageHeaders(Map.of())));

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }



}
