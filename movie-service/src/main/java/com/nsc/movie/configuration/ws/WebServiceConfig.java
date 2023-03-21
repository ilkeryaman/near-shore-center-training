package com.nsc.movie.configuration.ws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig {

    @Value("${soap.topdown.servletRegistration.urlMappings}")
    private String urlMappings;

    @Value("${soap.topdown.wsdlDefinition.schemaLocation}")
    private String schemaLocation;

    @Value("${soap.topdown.wsdlDefinition.locationUri}")
    private String locationUri;

    @Value("${soap.topdown.wsdlDefinition.portTypeName}")
    private String portTypeName;

    @Value("${soap.topdown.wsdlDefinition.targetNameSpace}")
    private String targetNamespace;

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, urlMappings);
    }

    @Bean(name = "movies")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema moviesSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName(portTypeName);
        wsdl11Definition.setLocationUri(locationUri);
        wsdl11Definition.setTargetNamespace(targetNamespace);
        wsdl11Definition.setSchema(moviesSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema moviesSchema() {
        return new SimpleXsdSchema(new ClassPathResource(schemaLocation));
    }
}
