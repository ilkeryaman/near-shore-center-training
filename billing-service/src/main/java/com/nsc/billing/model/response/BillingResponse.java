package com.nsc.billing.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BillingResponse<T> {
    private String code;
    private String message;
    private T data;
}
