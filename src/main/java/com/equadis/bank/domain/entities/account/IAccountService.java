package com.equadis.bank.domain.entities.account;

import java.util.NoSuchElementException;

public interface IAccountService {

    Account get(Long id);

    Account getByCustomer(Long customerId);

    Long create(Long customerId, Long initialAmount);

    void delete(Long id) throws NoSuchElementException;

}
