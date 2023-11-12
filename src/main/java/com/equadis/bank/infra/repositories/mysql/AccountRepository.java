package com.equadis.bank.infra.repositories.mysql;

import org.springframework.stereotype.Repository;

import com.equadis.bank.domain.entities.account.Account;
import com.equadis.bank.services.account.IAccountRepository;

@Repository
public class AccountRepository implements IAccountRepository {

    @Override
    public Account get(Long id) {
        // TODO Auto-generated method stub
        return new Account();
    }

    @Override
    public Account getByCostumer(Long customerId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByCostumer'");
    }

    @Override
    public Long save(Account account) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
}
