package com.nsc.billing.enums.messaging;

public enum KafkaTopic {
    NSC_CUSTOMER_CREATED("nsc.customer.created");

    private String value;

    KafkaTopic(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
