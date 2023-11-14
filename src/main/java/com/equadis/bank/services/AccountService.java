package com.equadis.bank.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.equadis.bank.domain.models.Account;
import com.equadis.bank.domain.models.Transaction;
import com.equadis.bank.domain.types.TransactionType;
import com.equadis.bank.domain.usecases.IAccountService;
import com.equadis.bank.infra.entities.AccountEntity;
import com.equadis.bank.infra.entities.TransactionEntity;
import com.equadis.bank.infra.repositories.IAccountRepository;
import com.equadis.bank.infra.repositories.ITransactionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {

    private final IAccountRepository repository;
    private final ITransactionRepository transactionRepository;
    private final ModelMapper mapper;

    @Override
    public Account get(Long id) {
        Optional<AccountEntity> opEntity = this.repository.findById(id);
        return opEntity.isPresent() ? mapper.map(opEntity, Account.class) : null;
    }

    @Override
    public List<Account> getByCustomer(Long customerId) {
        return this.repository.findByCustomerId(customerId)
                .stream()
                .map(entity -> mapper.map(entity, Account.class))
                .toList();
    }

    @Override
    public Long create(Long customerId, Long initialAmount) {
        Account account = new Account();
        account.setCustomerId(customerId);
        account.setBalance(initialAmount);
        AccountEntity entity = this.repository.save(mapper.map(account, AccountEntity.class));

        Transaction transaction = new Transaction(entity.getId(), TransactionType.DEPOSIT, initialAmount);
        this.transactionRepository.save(mapper.map(transaction, TransactionEntity.class));

        return entity.getId();
    }

    @Override
    public void delete(Long id) throws NoSuchElementException {
        Optional<AccountEntity> opEntity = this.repository.findById(id);

        if (opEntity.isPresent()) {
            AccountEntity account = opEntity.get();
            account.setActive(false);
            this.repository.save(account);
        } else {
            throw new NoSuchElementException();
        }
    }
}
