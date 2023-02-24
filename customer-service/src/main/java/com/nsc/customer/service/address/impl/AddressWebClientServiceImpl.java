package com.nsc.customer.service.address.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsc.customer.converter.mapstruct.IAddressMapper;
import com.nsc.customer.model.customer.Address;
import com.nsc.customer.service.address.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service("addressWebClientService")
public class AddressWebClientServiceImpl implements IAddressService {

    @Value("${rest.address-service.uri}")
    private String uri;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IAddressMapper addressMapper;

    @Autowired
    private WebClient webClient;

    @Override
    public List<Address> getListOfAddresses() {
        List<Address> listOfAddresses = new ArrayList<>();
        Mono<com.nsc.swagger.address_service_api.model.AddressResponse> monoResult =
                webClient.get().uri(uri).retrieve().bodyToMono(com.nsc.swagger.address_service_api.model.AddressResponse.class);
        com.nsc.swagger.address_service_api.model.AddressResponse addressResponse = monoResult.block();
        List<com.nsc.swagger.address_service_api.model.Address> addressList =
                objectMapper.convertValue(addressResponse.getData(), new TypeReference<>() { });
        addressList.stream().forEach(addr -> listOfAddresses.add(addressMapper.generateToModel(addr)));
        return listOfAddresses;
    }
}
