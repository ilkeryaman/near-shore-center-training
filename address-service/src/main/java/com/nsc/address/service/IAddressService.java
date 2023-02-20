package com.nsc.address.service;

import com.nsc.address.model.address.Address;

import java.util.List;

public interface IAddressService {
    List<Address> getAddressList();
    Address findAddressById(long id);
    boolean addAddress(Address address);
    boolean deleteAddress(long id);
}
