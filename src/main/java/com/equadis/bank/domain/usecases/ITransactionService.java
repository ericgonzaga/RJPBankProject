package com.equadis.bank.domain.usecases;

import java.util.List;

import com.equadis.bank.domain.errors.NotEnoughBalanceException;
import com.equadis.bank.domain.models.Transaction;

public interface ITransactionService {

    List<Transaction> listByAccount(Long accountId);

    void deposit(Long accountId, Long value);

    void withdraw(Long accountId, Long value) throws NotEnoughBalanceException;

    void transfer(Long accountIdFrom, Long accountIdTo, Long value) throws NotEnoughBalanceException;
}
