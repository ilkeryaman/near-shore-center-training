package com.nsc.customer.service.address.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsc.customer.mapstruct.IAddressMapper;
import com.nsc.customer.service.address.IAddressService;
import com.nsc.swagger.address_service_api.model.Address;
import com.nsc.swagger.address_service_api.model.AddressResponse;
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
    public List<com.nsc.customer.model.customer.Address> getListOfAddresses() {
        List<com.nsc.customer.model.customer.Address> listOfAddresses = new ArrayList<>();
        Mono<AddressResponse> monoResult =
                webClient.get().uri(uri).retrieve().bodyToMono(AddressResponse.class);
        AddressResponse addressResponse = monoResult.block();
        List<Address> addressList =
                objectMapper.convertValue(addressResponse.getData(), new TypeReference<>() { });
        addressList.stream().forEach(addr -> listOfAddresses.add(addressMapper.generateToModel(addr)));
        return listOfAddresses;
    }
}
