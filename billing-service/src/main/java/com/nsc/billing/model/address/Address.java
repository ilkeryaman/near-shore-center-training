package com.nsc.billing.model.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private long id;
    @JsonIgnore
    private String city;
    @JsonIgnore
    private String district;
}
