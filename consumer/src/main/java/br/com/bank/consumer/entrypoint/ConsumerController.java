package br.com.bank.consumer.entrypoint;

import br.com.bank.consumer.entrypoint.dto.ConsumerResponse;
import br.com.bank.consumer.entrypoint.dto.CreateConsumerRequest;
import br.com.bank.consumer.use_case.CreateConsumerUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/consumer")
@RequiredArgsConstructor
public class ConsumerController {

    private final CreateConsumerUseCase createConsumerUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsumerResponse create(@RequestBody final CreateConsumerRequest accountRequest) {
        log.info("receiving a create account request {}", accountRequest); // isso pode virar um interceptor ;)

        final var consumerSaved = createConsumerUseCase.create(accountRequest.toConsumerEntity());

        return new ConsumerResponse(consumerSaved.id(), consumerSaved.name(), consumerSaved.birthDate());
    }

}
