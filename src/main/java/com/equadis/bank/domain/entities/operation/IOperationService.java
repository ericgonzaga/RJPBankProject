package com.equadis.bank.domain.entities.operation;

import java.util.List;

import com.equadis.bank.domain.errors.NotEnoughBalanceException;

public interface IOperationService {
    List<Operation> listByCustomer(Long customerId);

    void deposit(Long customerId, Long value);

    void withdraw(Long customerId, Long value) throws NotEnoughBalanceException;

    void transfer(Long customerIdFrom, Long customerIdTo, Long value) throws NotEnoughBalanceException;
}
