package br.com.bank.account.entrypoint;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConsumerEventsListener implements Consumer<Message<MessageBody>> {
    @Override
    public void accept(Message<MessageBody> messageBodyMessage) {
        log.info("payload = {}", messageBodyMessage.getPayload());
    }
}
