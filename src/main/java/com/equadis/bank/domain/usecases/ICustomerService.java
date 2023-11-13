package com.equadis.bank.domain.usecases;

import java.util.List;
import java.util.NoSuchElementException;

import com.equadis.bank.domain.models.Customer;

public interface ICustomerService {

    public List<Customer> list();

    public Customer get(Long id);

    public Long create(String name);

    public void delete(Long id) throws NoSuchElementException;
}
