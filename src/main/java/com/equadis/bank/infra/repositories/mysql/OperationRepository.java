package com.equadis.bank.infra.repositories.mysql;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.equadis.bank.domain.entities.operation.Operation;
import com.equadis.bank.services.operation.IOperationRepository;

@Repository
public class OperationRepository implements IOperationRepository {

    @Override
    public List<Operation> getByCustomer(Long customerId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByCustomer'");
    }

    @Override
    public void save(Operation operation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}
