package com.equadis.bank.services.operation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.equadis.bank.domain.entities.account.Account;
import com.equadis.bank.domain.entities.operation.IOperationService;
import com.equadis.bank.domain.entities.operation.Operation;
import com.equadis.bank.domain.entities.operation.OperationType;
import com.equadis.bank.domain.errors.NotEnoughBalanceException;
import com.equadis.bank.services.account.IAccountRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OperationService implements IOperationService {

    public final IOperationRepository repository;
    private final IAccountRepository accountRepository;

    @Override
    public List<Operation> listByCustomer(Long customerId) {
        return this.repository.getByCustomer(customerId);
    }

    // TODO: Create transactional context in bellow methods to be sure that all
    // operation happens only if Account and Operation are correctly saved.

    @Override
    public void deposit(Long customerId, Long value) {
        Account account = this.accountRepository.getByCostumer(customerId);
        account.addBalance(value);
        this.accountRepository.save(account);

        Operation operation = new Operation(customerId, OperationType.DEPOSIT, value);
        this.repository.save(operation);
    }

    @Override
    public void withdraw(Long customerId, Long value) throws NotEnoughBalanceException {
        Account account = this.accountRepository.getByCostumer(customerId);
        account.deductBalance(value);
        this.accountRepository.save(account);

        Operation operation = new Operation(customerId, OperationType.WITHDRAW, value);
        this.repository.save(operation);
    }

    @Override
    public void transfer(Long customerIdFrom, Long customerIdTo, Long value) throws NotEnoughBalanceException {
        Account accountFrom = this.accountRepository.getByCostumer(customerIdFrom);
        Account accountTo = this.accountRepository.getByCostumer(customerIdTo);
        Account.transfer(accountFrom, accountTo, value);
        this.accountRepository.save(accountFrom);
        this.accountRepository.save(accountTo);

        Operation operationFrom = new Operation(customerIdFrom, OperationType.TRANSFER, value);
        Operation operationTo = new Operation(customerIdFrom, OperationType.DEPOSIT, value);
        this.repository.save(operationFrom);
        this.repository.save(operationTo);
    }

}
