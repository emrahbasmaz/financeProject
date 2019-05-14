package com.barstech.financeProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
    @Bean
    protected RestTemplate getTemplate() {
        return new RestTemplate();
    }
}
