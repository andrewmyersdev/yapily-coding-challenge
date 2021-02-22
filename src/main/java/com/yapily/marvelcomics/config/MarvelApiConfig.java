package com.yapily.marvelcomics.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;


@Configuration
public class MarvelApiConfig {

    @Value("${marvel.privateApiKey}")
    private String privateApiKey;

    @Value("${marvel.publicApiKey}")
    private String publicApiKey;

    @Value("${marvel.apiUrl}")
    private String apiUrl;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .uriTemplateHandler(new DefaultUriBuilderFactory(apiUrl))
                .interceptors(new MarvelApiRequestInterceptor(publicApiKey, privateApiKey))
                .errorHandler(new MarvelApiErrorHandler())
                .build();
    }

}

