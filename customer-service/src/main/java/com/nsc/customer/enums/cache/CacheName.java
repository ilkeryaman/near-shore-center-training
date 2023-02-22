package com.nsc.customer.enums.cache;

public enum CacheName {
    ADDRESS("address");

    private String value;

    CacheName(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
