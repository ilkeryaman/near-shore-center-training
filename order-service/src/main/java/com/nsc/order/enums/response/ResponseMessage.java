package com.nsc.order.enums.response;

public enum ResponseMessage {
    OK("Operation completed successfully!"),
    GENERAL_ERROR("An error occured!");

    private String value;

    ResponseMessage(String value){
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
