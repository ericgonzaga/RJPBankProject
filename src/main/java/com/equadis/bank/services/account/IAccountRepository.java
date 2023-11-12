package com.equadis.bank.services.account;

import com.equadis.bank.domain.entities.account.Account;

public interface IAccountRepository {

    Account get(Long id);

    Account getByCostumer(Long customerId);

    Long save(Account account);
}
