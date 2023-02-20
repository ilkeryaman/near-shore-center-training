package com.nsc.customer.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerResponse<T> {
    private String code;
    private String message;
    private T data;
}
