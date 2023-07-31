package br.com.bank.wallet.entrypoint.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@Data
public class MessageBody {

    private String event;

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "event")
    @JsonSubTypes(value = {
            @JsonSubTypes.Type(
                    value = NewConsumerCreatedEventSource.class, name = NewConsumerCreatedEventSource.EVENT_TYPE
            )
    })
    private Event source;


}
