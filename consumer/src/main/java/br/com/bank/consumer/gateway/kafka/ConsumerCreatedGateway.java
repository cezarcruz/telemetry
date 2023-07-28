package br.com.bank.consumer.gateway.kafka;

import br.com.bank.consumer.gateway.kafka.events.KafkaMessage;
import br.com.bank.consumer.gateway.kafka.factories.ConsumerEventSourceFactory;
import br.com.bank.consumer.gateway.mongo.entity.ConsumerEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerCreatedGateway {

    private final KafkaTemplate<String, String> kafkaTemplate;
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

            kafkaTemplate.send(event.getTopicName(), message);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @PostConstruct
    public void post() {
      this.kafkaTemplate.setObservationEnabled(true);
    }

}
