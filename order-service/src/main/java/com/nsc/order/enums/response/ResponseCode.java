package com.nsc.order.enums.response;

public enum ResponseCode {
    OK("OS_100"),
    GENERAL_ERROR("OS_001");

    private String value;

    ResponseCode(String value){
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}