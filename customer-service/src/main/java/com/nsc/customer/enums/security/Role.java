package com.nsc.customer.enums.security;

public enum Role {
    CUSTOMER_LISTER("customer_lister"),
    CUSTOMER_CREATOR("customer_creator");

    private String value;

    Role(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}