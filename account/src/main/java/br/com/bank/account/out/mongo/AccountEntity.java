package br.com.bank.account.out.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("accounts")
public record AccountEntity (
        @Id String id,
        String name
) {}
