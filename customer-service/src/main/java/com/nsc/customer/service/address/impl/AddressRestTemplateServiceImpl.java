package com.nsc.customer.service.address.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsc.customer.converter.mapstruct.IAddressMapper;
import com.nsc.customer.model.customer.Address;
import com.nsc.customer.service.address.IAddressService;
import com.nsc.customer.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AddressRestTemplateServiceImpl implements IAddressService {

    @Value("${rest.address-service.uri}")
    private String uri;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    IAddressMapper addressMapper;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public List<Address> getListOfAddresses() {
        List<Address> listOfAddresses = new ArrayList<>();

        ResponseEntity<com.nsc.swagger.address_service_api.model.AddressResponse> restResult =
                restTemplate.exchange(uri, HttpMethod.GET, HttpUtil.getHttpEntity(), com.nsc.swagger.address_service_api.model.AddressResponse.class);

        com.nsc.swagger.address_service_api.model.AddressResponse addressResponse = restResult.getBody();
        List<com.nsc.swagger.address_service_api.model.Address> addressList =
                objectMapper.convertValue(addressResponse.getData(), new TypeReference<>() { });
        addressList.stream().forEach(addr -> listOfAddresses.add(addressMapper.generateToModel(addr)));
        return listOfAddresses;
    }
}
