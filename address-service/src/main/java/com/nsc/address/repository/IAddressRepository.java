package com.nsc.address.repository;

import com.nsc.address.model.address.Address;

import java.util.List;

public interface IAddressRepository {
    List<Address> findAll();
    Address findById(Long id);
    void create(Address address);
    void update(Address address);
    void delete(Long id);
}
