package com.nsc.customer.repository.impl;

import com.nsc.customer.model.customer.Customer;
import com.nsc.customer.repository.ICustomerRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements ICustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Customer> findAll() {
        return entityManager.createQuery("from Customer", Customer.class).getResultList();
    }

    @Override
    public Customer findById(Long id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public Customer create(Customer customer) {
        return entityManager.merge(customer);
    }

    @Override
    public void update(Customer customer) {
        entityManager.merge(customer);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.getReference(Customer.class, id));
    }
}
