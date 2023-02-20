package com.nsc.customer.exception.handler;

import com.nsc.customer.enums.response.ResponseCode;
import com.nsc.customer.enums.response.ResponseMessage;
import com.nsc.customer.exception.CustomerNotFoundException;
import com.nsc.customer.model.response.CustomerResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class CustomerServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public final ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException ex, WebRequest request) {
        CustomerResponse customerResponse = new CustomerResponse(ResponseCode.CUSTOMER_NOT_FOUND.getValue(), ResponseMessage.CUSTOMER_NOT_FOUND.getValue(), null);
        return new ResponseEntity(customerResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Optional<ObjectError> errorOptional = ex.getBindingResult().getAllErrors().stream().findFirst();
        CustomerResponse customerResponse = new CustomerResponse(
                ResponseCode.BAD_REQUEST.getValue(),
                errorOptional.isPresent() ? errorOptional.get().getDefaultMessage() : ResponseMessage.BAD_REQUEST.getValue(),
                null);
        return new ResponseEntity(customerResponse, HttpStatus.BAD_REQUEST);
    }
}
