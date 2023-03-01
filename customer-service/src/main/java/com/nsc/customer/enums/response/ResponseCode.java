package com.nsc.customer.enums.response;

public enum ResponseCode {
    OK("CS_100"),
    GENERAL_ERROR("CS_001"),
    BAD_REQUEST("CS_102"),
    CUSTOMER_NOT_FOUND("CS_103"),
    ADDRESS_NOT_FOUND("CS_104"),
    ADDRESS_SERVICE_ERROR("CS_105"),
    NB_AUTHENTICATION_EXCEPTION("CS_106"),
    SB_AUTHENTICATION_EXCEPTION("CS_107");

    private String value;

    ResponseCode(String value){
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
