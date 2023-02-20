package com.nsc.address.configuration.rest.openapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(title = "Address Service API", version = "1.0", description = "Address service for Java Trainings."))
@Configuration
public class OpenApiConfig { }
