package br.com.bank.account.entrypoint;

import io.micrometer.observation.Observation;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConsumerEventsListener {

    private final RestTemplateBuilder builder;

    @KafkaListener(topics = "consumer-events", groupId = "penis")
    public void onMessage(Message message) {
        log.info("receiving message {}", message);

        var x = builder.build();

        final var forEntity = x.getForEntity("http://localhost:8080/consumer/64bc2b36525cbd62370d0af5", String.class);

        log.info("xpto from entity {}", forEntity);
    }
}
