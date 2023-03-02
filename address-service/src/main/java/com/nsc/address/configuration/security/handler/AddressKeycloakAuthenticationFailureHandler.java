package com.nsc.address.configuration.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.nsc.address.enums.response.ResponseCode;
import com.nsc.address.enums.response.ResponseMessage;
import com.nsc.address.model.response.AddressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AddressKeycloakAuthenticationFailureHandler implements AuthenticationFailureHandler, AccessDeniedHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        AddressResponse addressResponse = new AddressResponse(ResponseCode.NB_AUTHENTICATION_EXCEPTION.getValue(), ResponseMessage.NB_AUTHENTICATION_EXCEPTION.getValue(), null);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String responseJson = objectWriter.writeValueAsString(addressResponse);
        response.getOutputStream().println(responseJson);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        AddressResponse addressResponse = new AddressResponse(ResponseCode.NB_AUTHORIZATION_EXCEPTION.getValue(), ResponseMessage.NB_AUTHORIZATION_EXCEPTION.getValue(), null);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String responseJson = objectWriter.writeValueAsString(addressResponse);
        response.getOutputStream().println(responseJson);
    }
}