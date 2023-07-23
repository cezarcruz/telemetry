package br.com.bank.consumer.gateway.kafka.events;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsumerCreatedEvent implements EventSourceProducer {

    private static final String TOPIC = "consumer-events";
    private static final String EVENT_TYPE = "CONSUMER_CREATED";


    private String id;
    private String name;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    @Override
    public String getTopicName() {
        return TOPIC;
    }

    @Override
    public String getEvent() {
        return TOPIC;
    }
}
