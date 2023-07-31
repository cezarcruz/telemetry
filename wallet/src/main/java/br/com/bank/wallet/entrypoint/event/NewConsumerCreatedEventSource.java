package br.com.bank.wallet.entrypoint.event;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NewConsumerCreatedEventSource implements Event {

    public static final String EVENT_TYPE = "NEW_CONSUMER";
    private String name;
    private LocalDate birthDate;

    @Override
    public String getEventType() {
        return EVENT_TYPE;
    }
}
