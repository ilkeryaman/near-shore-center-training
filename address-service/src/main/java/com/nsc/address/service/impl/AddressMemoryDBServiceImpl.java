package com.nsc.address.service.impl;

import com.nsc.address.enums.address.District;
import com.nsc.address.service.IAddressService;
import com.nsc.address.model.address.Address;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service("addressMemoryDBService")
public class AddressMemoryDBServiceImpl implements IAddressService {

    private List<Address> listOfAddresses;

    AddressMemoryDBServiceImpl(){
        listOfAddresses = new ArrayList<>();
    }

    @PostConstruct
    private void initData(){
        listOfAddresses.addAll(
                Arrays.asList(
                        new Address(1, District.AVCILAR.getCity().getValue(), District.AVCILAR.getValue()),
                        new Address(1, District.BAHCELIEVLER.getCity().getValue(), District.BAHCELIEVLER.getValue()),
                        new Address(1, District.CESME.getCity().getValue(), District.CESME.getValue()),
                        new Address(1, District.URLA.getCity().getValue(), District.URLA.getValue()),
                        new Address(1, District.CANKAYA.getCity().getValue(), District.CANKAYA.getValue())
                )
        );
    }

    public List<Address> getAddressList(){
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
