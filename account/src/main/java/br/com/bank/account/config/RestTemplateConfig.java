package br.com.bank.account.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


public class RestTemplateConfig {

    //@Bean
    RestTemplate restTemplate(final RestTemplateBuilder builder) {
        return builder.build();
    }
}
