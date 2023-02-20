package com.nsc.customer.enums.response;

public enum ResponseMessage {
    OK("Operation completed successfully!"),
    GENERAL_ERROR("An error occured!"),
    BAD_REQUEST("Please check request parameters!"),
    CUSTOMER_NOT_FOUND("Customer not found!");

    private String value;

    ResponseMessage(String value){
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}