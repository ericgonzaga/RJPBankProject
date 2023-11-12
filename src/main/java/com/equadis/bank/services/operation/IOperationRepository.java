package com.equadis.bank.services.operation;

import java.util.List;

import com.equadis.bank.domain.entities.operation.Operation;

public interface IOperationRepository {

    public List<Operation> getByCustomer(Long customerId);

    public void save(Operation operation);
}
