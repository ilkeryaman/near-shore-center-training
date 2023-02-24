package com.nsc.customer.configuration.rest.resttemplate;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @ConfigurationProperties("spring.security.oauth2.client.address-api")
    protected ClientCredentialsResourceDetails addressApiResourceDetails() {
        return new ClientCredentialsResourceDetails();
    }

    @Bean
    public RestTemplate restTemplate() {
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(addressApiResourceDetails(), new DefaultOAuth2ClientContext());
        //restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        ClientCredentialsAccessTokenProvider provider = new ClientCredentialsAccessTokenProvider();
        restTemplate.setAccessTokenProvider(provider);
        return restTemplate;
    }

}
