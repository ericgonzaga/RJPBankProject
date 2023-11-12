package com.equadis.bank.infra.repositories.mysql;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.equadis.bank.domain.entities.customer.Customer;
import com.equadis.bank.services.customer.ICustomerRepository;

@Repository
public class CustomerRepository implements ICustomerRepository {

    @Override
    public Customer get(Long id) {
        // TODO Auto-generated method stub
        return new Customer();
    }

    @Override
    public List<Customer> list() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'list'");
    }

    @Override
    public Long save(Customer customer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}
