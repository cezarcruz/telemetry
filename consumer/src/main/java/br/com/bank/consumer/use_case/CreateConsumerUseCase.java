package br.com.bank.consumer.use_case;

import br.com.bank.consumer.gateway.kafka.ConsumerCreatedGateway;
import br.com.bank.consumer.gateway.mongo.ConsumerRepository;
import br.com.bank.consumer.gateway.mongo.entity.ConsumerEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateConsumerUseCase {

    private final ConsumerRepository consumerRepository;
    private final ConsumerCreatedGateway consumerCreatedGateway;

    public ConsumerEntity create(final ConsumerEntity consumerEntity) {
        final var consumerSaved = consumerRepository.save(consumerEntity);
        log.info("consumer created {}", consumerSaved);

        consumerCreatedGateway.notify(consumerSaved);

        return consumerSaved;
    }

}
