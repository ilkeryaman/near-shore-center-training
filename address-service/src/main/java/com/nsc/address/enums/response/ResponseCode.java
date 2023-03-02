package com.nsc.address.enums.response;

public enum ResponseCode {
    OK("AS_100"),
    GENERAL_ERROR("AS_001"),
    BAD_REQUEST("AS_102"),
    ADDRESS_NOT_FOUND("AS_103"),
    NB_AUTHENTICATION_EXCEPTION("AS_104"),
    NB_AUTHORIZATION_EXCEPTION("AS-105");

    private String value;

    ResponseCode(String value){
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
