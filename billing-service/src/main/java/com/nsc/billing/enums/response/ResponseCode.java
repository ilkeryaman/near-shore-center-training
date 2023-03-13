package com.nsc.billing.enums.response;

public enum ResponseCode {
    OK("BS_100"),
    GENERAL_ERROR("BS_001"),
    BAD_REQUEST("BS_102");

    private String value;

    ResponseCode(String value){
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
