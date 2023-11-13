package com.equadis.bank.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.equadis.bank.domain.errors.NotEnoughBalanceException;
import com.equadis.bank.domain.models.Account;
import com.equadis.bank.domain.models.Transaction;
import com.equadis.bank.domain.types.TransactionType;
import com.equadis.bank.domain.usecases.ITransactionService;
import com.equadis.bank.infra.entities.AccountEntity;
import com.equadis.bank.infra.entities.TransactionEntity;
import com.equadis.bank.infra.repositories.IAccountRepository;
import com.equadis.bank.infra.repositories.ITransactionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransactionService implements ITransactionService {

    public final ITransactionRepository repository;
    public final IAccountRepository accountRepository;
    private final AccountService accountService;
    private final ModelMapper mapper;

    @Override
    public List<Transaction> listByAccount(Long accountId) {
        return this.repository.findByAccountId(accountId)
                .stream()
                .map(entity -> this.mapper.map(entity, Transaction.class))
                .toList();
    }

    // TODO: Create transactional context in bellow methods to be sure that all
    // transaction happens only if Account and Transaction are correctly saved.

    @Override
    public void deposit(Long accountId, Long value) {
        Account account = this.accountService.get(accountId);
        if (account == null) {
            throw new NoSuchElementException("accountId");
        }

        account.addBalance(value);
        this.accountRepository.save(this.mapper.map(account, AccountEntity.class));

        Transaction transaction = new Transaction(accountId, TransactionType.DEPOSIT, value);
        this.repository.save(this.mapper.map(transaction, TransactionEntity.class));
    }

    @Override
    public void withdraw(Long accountId, Long value) throws NotEnoughBalanceException {
        Account account = this.accountService.get(accountId);
        if (account == null) {
            throw new NoSuchElementException("accountId");
        }

        account.deductBalance(value);
        this.accountRepository.save(this.mapper.map(account, AccountEntity.class));

        Transaction transaction = new Transaction(accountId, TransactionType.WITHDRAW, value);
        this.repository.save(this.mapper.map(transaction, TransactionEntity.class));
    }

    @Override
    public void transfer(Long accountIdFrom, Long accountIdTo, Long value) throws NotEnoughBalanceException {
        Account accountFrom = this.accountService.get(accountIdFrom);
        Account accountTo = this.accountService.get(accountIdTo);
        Account.transfer(accountFrom, accountTo, value);
        this.accountRepository.save(this.mapper.map(accountFrom, AccountEntity.class));
        this.accountRepository.save(this.mapper.map(accountTo, AccountEntity.class));

        Transaction transactionFrom = new Transaction(accountIdFrom, TransactionType.TRANSFER, value);
        Transaction transactionTo = new Transaction(accountIdTo, TransactionType.DEPOSIT, value);
        this.repository.save(this.mapper.map(transactionFrom, TransactionEntity.class));
        this.repository.save(this.mapper.map(transactionTo, TransactionEntity.class));
    }

}
