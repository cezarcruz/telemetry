package br.com.bank.consumer.gateway.kafka.factories;

import br.com.bank.consumer.gateway.kafka.events.ConsumerCreatedEvent;
import br.com.bank.consumer.gateway.mongo.entity.ConsumerEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConsumerEventSourceFactory {

    public static ConsumerCreatedEvent from(final ConsumerEntity consumerEntity) {
        return ConsumerCreatedEvent.builder()
                .name(consumerEntity.name())
                .birthDate(consumerEntity.birthDate())
                .id(consumerEntity.id())
                .build();
    }

}
