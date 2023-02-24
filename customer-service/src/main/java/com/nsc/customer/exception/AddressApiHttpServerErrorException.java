package com.nsc.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class AddressApiHttpServerErrorException extends HttpServerErrorException {

    public AddressApiHttpServerErrorException(HttpStatus statusCode) {
        super(statusCode);
    }
}
