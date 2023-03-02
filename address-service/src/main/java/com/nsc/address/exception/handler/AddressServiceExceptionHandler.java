package com.nsc.address.exception.handler;

import com.nsc.address.enums.response.ResponseCode;
import com.nsc.address.enums.response.ResponseMessage;
import com.nsc.address.exception.AddressNotFoundException;
import com.nsc.address.model.response.AddressResponse;
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
public class AddressServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AddressNotFoundException.class)
    public final ResponseEntity<Object> handleAddressNotFoundException(AddressNotFoundException ex, WebRequest request) {
        AddressResponse addressResponse = new AddressResponse(ResponseCode.ADDRESS_NOT_FOUND.getValue(), ResponseMessage.ADDRESS_NOT_FOUND.getValue(), null);
        return new ResponseEntity(addressResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleUnhandledException(Exception ex, WebRequest request) {
        AddressResponse addressResponse = new AddressResponse(ResponseCode.GENERAL_ERROR.getValue(), ResponseMessage.GENERAL_ERROR.getValue(), null);
        return new ResponseEntity(addressResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Optional<ObjectError> errorOptional = ex.getBindingResult().getAllErrors().stream().findFirst();
        AddressResponse addressResponse = new AddressResponse(
                ResponseCode.BAD_REQUEST.getValue(),
                errorOptional.isPresent() ? errorOptional.get().getDefaultMessage() : ResponseMessage.BAD_REQUEST.getValue(),
                null);
        return new ResponseEntity(addressResponse, HttpStatus.BAD_REQUEST);
    }
}
