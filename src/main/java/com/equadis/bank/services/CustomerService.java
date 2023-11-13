package com.equadis.bank.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.equadis.bank.domain.models.Customer;
import com.equadis.bank.domain.usecases.ICustomerService;
import com.equadis.bank.infra.entities.CustomerEntity;
import com.equadis.bank.infra.repositories.ICustomerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {

    private final ICustomerRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<Customer> list() {
        return this.repository.findAll()
                .stream()
                .map(entity -> this.mapper.map(entity, Customer.class))
                .toList();
    }

    @Override
    public Customer get(Long id) {
        Optional<CustomerEntity> opEntity = this.repository.findById(id);
        return opEntity.isPresent() ? this.mapper.map(opEntity, Customer.class) : null;
    }

    @Override
    public Long create(String name) {
        Customer customer = new Customer();
        customer.setName(name);
        return this.repository.save(this.mapper.map(customer, CustomerEntity.class)).getId();
    }

    @Override
    public void delete(Long id) throws NoSuchElementException {
        Customer customer = this.get(id);

        if (customer != null) {
            customer.setActive(false);
            this.repository.save(this.mapper.map(customer, CustomerEntity.class));
        } else {
            throw new NoSuchElementException();
        }
    }

}
