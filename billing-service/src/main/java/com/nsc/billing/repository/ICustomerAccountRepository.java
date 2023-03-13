package com.nsc.billing.repository;

import com.nsc.billing.model.account.CustomerAccount;
import com.nsc.billing.model.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {
    List<CustomerAccount> findByAddress(Address address);
    @Query(name = "findByCustomerNo", value = "select ca from customerAccount ca inner join ca.customer c where c.customerNo = ?1")
    List<CustomerAccount> findByCustomerNo(String customerNo);
}
