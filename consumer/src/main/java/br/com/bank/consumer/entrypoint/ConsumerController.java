package br.com.bank.consumer.entrypoint;

import br.com.bank.consumer.entrypoint.dto.ConsumerResponse;
import br.com.bank.consumer.entrypoint.dto.CreateConsumerRequest;
import br.com.bank.consumer.gateway.mongo.ConsumerRepository;
import br.com.bank.consumer.use_case.CreateConsumerUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/consumer")
@RequiredArgsConstructor
public class ConsumerController {

    private final CreateConsumerUseCase createConsumerUseCase;
    private final ConsumerRepository consumerRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsumerResponse create(@RequestBody final CreateConsumerRequest accountRequest) {
        log.info("receiving a create account request {}", accountRequest); // isso pode virar um interceptor ;)

        final var consumerSaved = createConsumerUseCase.create(accountRequest.toConsumerEntity());

        MDC.clear();

        return new ConsumerResponse(consumerSaved.id(), consumerSaved.name(), consumerSaved.birthDate());
    }

    @GetMapping("/{id}")
    public ConsumerResponse getById(@PathVariable final String id) {
        return consumerRepository.findById(id)
                .map(consumer -> new ConsumerResponse(consumer.id(), consumer.name(), consumer.birthDate()))
                .orElseThrow();
    }

}
