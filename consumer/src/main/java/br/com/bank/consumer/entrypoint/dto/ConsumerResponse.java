package br.com.bank.consumer.entrypoint.dto;

import java.time.LocalDate;

public record ConsumerResponse(
        String id,
        String name,
        LocalDate birthDate
) {}
