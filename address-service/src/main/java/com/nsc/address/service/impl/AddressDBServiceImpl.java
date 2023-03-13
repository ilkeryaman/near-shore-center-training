package com.nsc.address.service.impl;

import com.nsc.address.model.address.Address;
import com.nsc.address.repository.IAddressRepository;
import com.nsc.address.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("addressDBService")
public class AddressDBServiceImpl implements IAddressService {

    @Autowired
    private IAddressRepository repository;

    @Override
    public List<Address> getAddressList() {
        return repository.findAll();
    }

    @Override
    public Address findAddressById(long id) {
        return repository.findById(id);
    }

    @Override
    public boolean addAddress(Address address) {
        repository.create(address);
        return true;
    }

    @Override
    public boolean deleteAddress(long id) {
        repository.delete(id);
        return true;
    }
}
