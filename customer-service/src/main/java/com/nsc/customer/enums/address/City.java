package com.nsc.customer.enums.address;

public enum City {
    ISTANBUL("İstanbul"),
    IZMIR("İzmir"),
    ANKARA("Ankara");

    private String value;

    City(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
