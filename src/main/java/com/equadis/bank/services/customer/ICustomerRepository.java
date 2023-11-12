package com.equadis.bank.services.customer;

import java.util.List;

import com.equadis.bank.domain.entities.customer.Customer;

public interface ICustomerRepository {

    public Customer get(Long id);

    public List<Customer> list();

    public Long save(Customer customer);
}
