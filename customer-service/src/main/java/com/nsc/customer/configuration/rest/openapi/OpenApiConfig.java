package com.nsc.customer.configuration.rest.openapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(title = "Customer Service API", version = "1.0", description = "Customer service for Java Trainings."))
@Configuration
public class OpenApiConfig { }
