package com.nsc.customer.enums.resilience;

public enum Instance {

    GET_LIST_OF_ADDRESSES("getListOfAddresses");

    private String value;

    Instance(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
