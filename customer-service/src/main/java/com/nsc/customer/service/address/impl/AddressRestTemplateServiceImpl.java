package com.nsc.customer.service.address.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsc.customer.mapstruct.IAddressMapper;
import com.nsc.customer.service.address.IAddressService;
import com.nsc.customer.util.HttpUtil;
import com.nsc.swagger.address_service_api.model.AddressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("addressRestTemplateService")
public class AddressRestTemplateServiceImpl implements IAddressService {

    @Value("${rest.address-service.uri}")
    private String uri;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IAddressMapper addressMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<com.nsc.customer.model.customer.Address> getListOfAddresses() {
        List<com.nsc.customer.model.customer.Address> listOfAddresses = new ArrayList<>();

        ResponseEntity<AddressResponse> restResult =
                restTemplate.exchange(uri, HttpMethod.GET, HttpUtil.getHttpEntity(), AddressResponse.class);

        AddressResponse addressResponse = restResult.getBody();
        List<com.nsc.swagger.address_service_api.model.Address> addressList =
                objectMapper.convertValue(addressResponse.getData(), new TypeReference<>() { });
        addressList.stream().forEach(addr -> listOfAddresses.add(addressMapper.generateToModel(addr)));
        return listOfAddresses;
    }
}
