package com.equadis.bank.domain.models;

import com.equadis.bank.domain.errors.NotEnoughBalanceException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Account {

    private Long id;
    private Long customerId;
    private Long balance;
    private Boolean active;

    public Account() {
        this.active = true;
    }

    public void addBalance(Long value) {
        this.balance += value;
    }

    public void deductBalance(Long value) throws NotEnoughBalanceException {
        if (value > this.balance) {
            throw new NotEnoughBalanceException();
        }

        this.balance -= value;
    }

    public static void transfer(Account from, Account to, Long value) throws NotEnoughBalanceException {
        from.deductBalance(value);
        to.addBalance(value);
    }
}
