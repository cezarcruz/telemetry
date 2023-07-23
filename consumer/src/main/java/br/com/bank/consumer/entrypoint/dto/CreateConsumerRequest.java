package br.com.bank.consumer.entrypoint.dto;

import br.com.bank.consumer.gateway.mongo.entity.ConsumerEntity;

import java.time.LocalDate;

public record CreateConsumerRequest(
        String name,
        LocalDate birthDate
) {

    public ConsumerEntity toConsumerEntity() {
        return new ConsumerEntity(name, birthDate);
    }
}
