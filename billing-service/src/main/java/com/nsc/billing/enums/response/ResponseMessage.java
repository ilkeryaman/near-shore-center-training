package com.nsc.billing.enums.response;

public enum ResponseMessage {
    OK("Operation completed successfully!"),
    GENERAL_ERROR("An error occured!"),
    BAD_REQUEST("Please check request parameters!");

    private String value;

    ResponseMessage(String value){
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
