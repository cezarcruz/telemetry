package br.com.bank.consumer.gateway.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("consumers")
public record ConsumerEntity (
        @Id
        String id,
        String name,
        LocalDate birthDate
) {

        public ConsumerEntity(final String name, final LocalDate birthDate) {
                this(null, name, birthDate);
        }

}
