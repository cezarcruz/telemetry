package br.com.bank.account.entrypoint.listener;

import br.com.bank.account.out.mongo.AccountEntity;
import br.com.bank.account.out.mongo.AccountRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConsumerEventsListener {

    private final AccountRepository accountRepository;
    private final RestTemplateBuilder builder;

    @KafkaListener(topics = "consumer-events", groupId = "penis")
    public void onMessage(Message message) {
        log.info("receiving message {}", message);

        var x = builder.build();

        final var forEntity = x.getForEntity("http://localhost:8080/consumer/64bc2b36525cbd62370d0af5", String.class);

        log.info("xpto from entity {}", forEntity);

        accountRepository.save(new AccountEntity(null, "Cezinha"));
    }
}
