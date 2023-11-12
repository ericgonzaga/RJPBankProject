package com.equadis.bank.services.customer;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.equadis.bank.domain.entities.customer.Customer;
import com.equadis.bank.domain.entities.customer.ICustomerService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {

    private final ICustomerRepository repository;

    @Override
    public List<Customer> list() {
        return this.repository.list();
    }

    @Override
    public Customer get(Long id) {
        return this.repository.get(id);
    }

    @Override
    public Long create(String name) {
        Customer customer = new Customer();
        customer.setName(name);
        return this.repository.save(customer);
    }

    @Override
    public void delete(Long id) throws NoSuchElementException {
        Customer customer = this.get(id);

        if (customer != null) {
            customer.setActive(false);
            this.repository.save(customer);
        } else {
            throw new NoSuchElementException();
        }
    }

}
