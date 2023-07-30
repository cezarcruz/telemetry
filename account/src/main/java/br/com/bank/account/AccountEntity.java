package br.com.bank.account;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("accounts")
public record AccountEntity (
        @Id String id,
        String name
) {}
