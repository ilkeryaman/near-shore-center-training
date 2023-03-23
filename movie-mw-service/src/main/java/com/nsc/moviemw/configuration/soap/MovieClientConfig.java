package com.nsc.moviemw.configuration.soap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class MovieClientConfig {

    @Value("${movie-service.uri}")
    private String uri;

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.nsc.moviemw.springsoap.client.gen");
        return marshaller;
    }

    @Bean
    public MovieClient movieClient(Jaxb2Marshaller marshaller) {
        MovieClient client = new MovieClient();
        client.setDefaultUri(uri);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
