package com.nsc.moviemw.enums.response;

public enum ResponseCode {
    OK("MS_100"),
    GENERAL_ERROR("MS_001"),;

    private String value;

    ResponseCode(String value){
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
