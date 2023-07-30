package br.com.bank.account.config;

import com.mongodb.ConnectionString;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.observability.ContextProviderFactory;
import org.springframework.data.mongodb.observability.MongoObservationCommandListener;

@Configuration
public class MongoConfig {
    @Bean
    MongoClientSettingsBuilderCustomizer mongoObservabilityCustomizer(ObservationRegistry observationRegistry,
                                                                      MongoProperties mongoProperties) {
        return clientSettingsBuilder -> clientSettingsBuilder
                .contextProvider(ContextProviderFactory.create(observationRegistry))
                .addCommandListener(new MongoObservationCommandListener(observationRegistry,
                        new ConnectionString(mongoProperties.determineUri())));

    }
}
