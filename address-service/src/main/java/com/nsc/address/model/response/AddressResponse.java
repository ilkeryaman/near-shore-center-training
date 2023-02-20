package com.nsc.address.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressResponse<T> {
    private String code;
    private String message;
    private T data;
}
