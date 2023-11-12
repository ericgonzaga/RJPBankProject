package com.equadis.bank.domain.entities.customer;

import java.util.List;
import java.util.NoSuchElementException;

public interface ICustomerService {

    public List<Customer> list();

    public Customer get(Long id);

    public Long create(String name);

    public void delete(Long id) throws NoSuchElementException;
}
