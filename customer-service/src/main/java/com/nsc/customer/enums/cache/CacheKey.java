package com.nsc.customer.enums.cache;

public enum CacheKey {
    ADDRESS_LIST("addressList");

    private String value;

    CacheKey(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
