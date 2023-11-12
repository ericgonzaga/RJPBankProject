package com.equadis.bank.services.account;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.equadis.bank.domain.entities.account.Account;
import com.equadis.bank.domain.entities.account.IAccountService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {

    private final IAccountRepository repository;

    @Override
    public Account get(Long id) {
        return this.repository.get(id);
    }

    @Override
    public Account getByCustomer(Long customerId) {
        return this.repository.getByCostumer(customerId);
    }

    @Override
    public Long create(Long customerId, Long initialAmount) {
        Account account = new Account();
        account.setCustomerId(customerId);
        account.setAmount(initialAmount);
        return this.repository.save(account);
    }

    @Override
    public void delete(Long id) throws NoSuchElementException {
        Account account = this.get(id);

        if (account != null) {
            account.setActive(false);
            this.repository.save(account);
        } else {
            throw new NoSuchElementException();
        }
    }
}
