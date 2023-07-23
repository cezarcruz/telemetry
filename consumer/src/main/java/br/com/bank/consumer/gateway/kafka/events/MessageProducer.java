package br.com.bank.consumer.gateway.kafka.events;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageProducer {

    public static <T> Message<T> toMessage(final T message, @NotNull final MessageHeaders headers) {
        return MessageBuilder.createMessage(message, headers);
    }

}
