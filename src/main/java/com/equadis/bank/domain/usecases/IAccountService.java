package com.equadis.bank.domain.usecases;

import java.util.List;
import java.util.NoSuchElementException;

import com.equadis.bank.domain.models.Account;

public interface IAccountService {

    Account get(Long id);

    List<Account> getByCustomer(Long customerId);

    Long create(Long customerId, Long initialAmount);

    void delete(Long id) throws NoSuchElementException;

}
