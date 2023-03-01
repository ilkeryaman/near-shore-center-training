package com.nsc.address.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsc.address.service.IAddressService;
import com.nsc.address.model.address.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service("addressFileService")
public class AddressFileServiceImpl implements IAddressService {

    private List<Address> listOfAddresses;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${address.file.url}")
    private String addressFileUrl;

    @PostConstruct
    private void initData() {
        try {
            listOfAddresses = objectMapper.readValue(new File(addressFileUrl), new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Address> getAddressList() {
        return listOfAddresses;
    }

    @Override
    public Address findAddressById(long id) {
        Optional<Address> addressOptional = listOfAddresses.stream().filter(address -> address.getId() == id).findFirst();
        return addressOptional.isPresent() ? addressOptional.get() : null;
    }

    @Override
    public boolean addAddress(Address address) {
        listOfAddresses.add(address);
        return true;
    }

    @Override
    public boolean deleteAddress(long id) {
        return listOfAddresses.removeIf(address -> address.getId() == id);
    }
}
