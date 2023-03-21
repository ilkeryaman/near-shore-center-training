package com.nsc.customer.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsc.customer.model.customer.Address;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AddressDataHelper {
    public static List<Address> getListOfAddresses(){
        List<Address> addressList = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            addressList = objectMapper.readValue(new File("src/test/resources/data/addresses.json"), new TypeReference<List<Address>>() { });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return addressList;
    }
}
