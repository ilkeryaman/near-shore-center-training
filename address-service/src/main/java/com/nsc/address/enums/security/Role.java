package com.nsc.address.enums.security;

public enum Role {
    ADDRESS_LISTER("address_lister"),
    ADDRESS_CREATOR("address_creator");

    private String value;

    Role(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}