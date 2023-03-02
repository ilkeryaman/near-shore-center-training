package com.nsc.address.enums.response;

public enum ResponseMessage {
    OK("Operation completed successfully!"),
    GENERAL_ERROR("An error occured!"),
    BAD_REQUEST("Please check request parameters!"),
    ADDRESS_NOT_FOUND("Address not found!"),
    NB_AUTHENTICATION_EXCEPTION("Authentication error occured for northbound!"),
    NB_AUTHORIZATION_EXCEPTION("Method not allowed!"),;

    private String value;

    ResponseMessage(String value){
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
