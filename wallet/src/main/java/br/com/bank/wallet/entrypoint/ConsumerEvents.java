package br.com.bank.wallet.entrypoint;


import br.com.bank.wallet.out.WalletEntity;
import br.com.bank.wallet.out.WalletRepository;
import brave.Tracing;
import brave.kafka.streams.KafkaStreamsTracing;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.math.BigDecimal;
import java.util.Properties;

//@Slf4j
//@Component
//@RequiredArgsConstructor
@Configuration
@Slf4j
@RequiredArgsConstructor
public class ConsumerEvents { // implements Consumer<Message<MessageBody>> {

    private final WalletRepository walletRepository;
    private final RestTemplateBuilder builder;

//    @Bean
//    public KStream<?, String> consumerEventsListener(final KStream<?, String > input) {
//        return input.peek((k, v) -> {
//            log.info("value {}", v);
//        });
//    }

//    @Bean
//    public Consumer<String> consumerEventsListener() {
//       return s -> log.info("value {}", s);
//    }

    @Bean
    public KafkaStreams kafkaStreams(KafkaProperties kafkaProperties,
                                     @Value("${spring.application.name}") String appName,
                                     Tracing tracing) {

        var kafkaStreamsTracing = KafkaStreamsTracing.create(tracing);

        final Properties props = new Properties();

        // inject SSL related properties
        props.putAll(kafkaProperties.getSsl().buildProperties());
        props.putAll(kafkaProperties.getProperties());
        // stream config centric ones
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, appName);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, JsonSerde.class);
        props.put(StreamsConfig.STATE_DIR_CONFIG, "data");
        // others
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, JsonNode.class);

        final KafkaStreams kafkaStreams = kafkaStreamsTracing.kafkaStreams(kafkaStreamTopology(), props);// new KafkaStreams(kafkaStreamTopology(), props);
        kafkaStreams.start();

        return kafkaStreams;
    }

    @Bean
    public Topology kafkaStreamTopology() {
        final StreamsBuilder streamsBuilder = new StreamsBuilder();

         streamsBuilder.stream("consumer-events")
                 .peek((k, v) -> {
                     log.info("k, v {}, {}", k,v);
                     walletRepository.save(new WalletEntity(null, BigDecimal.ZERO));

                     var x = builder.build();

                     final var forEntity = x.getForEntity("http://localhost:8080/consumer/64bc2b36525cbd62370d0af5", String.class);

                     log.info("xpto from entity {}", forEntity);
                 });

        return streamsBuilder.build();
    }

//    @Bean
//    public Consumer<KStream<Long, String>> consumerEventsListener() {
//        return s -> s.peek((k, v) -> {
//            log.info("value {}", v);
//        });
//    }
//
//    @Bean
//    public Consumer<String> consumerEventsListener(final Tracer tracer) {
//        return s -> log.info("String {} {}", s, tracer.currentSpan().context().traceId());
//    }

//    @Bean
//    public ProducerMessageHandlerCustomizer<KafkaProducerMessageHandler> sourceCustomizer() {
//        return (source, dest, group) -> source.setPropertiesConverter(customPropertiesConverter);
//    }



//    @SneakyThrows
//    @Override
//    public void accept(Message<MessageBody> messageBodyMessage) {
//        log.info("payload = {}", new ObjectMapper().writeValueAsString(messageBodyMessage.getPayload()));
//    }
}
