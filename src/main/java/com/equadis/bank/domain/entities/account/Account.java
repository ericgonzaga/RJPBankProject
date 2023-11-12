package com.equadis.bank.domain.entities.account;

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
    private Long amount;
    private Boolean active;

    public Account() {
        this.active = true;
    }

    public void addBalance(Long value) {
        this.amount += value;
    }

    public void deductBalance(Long value) throws NotEnoughBalanceException {
        if (value > this.amount) {
            throw new NotEnoughBalanceException();
        }

        this.amount -= value;
    }

    public static void transfer(Account from, Account to, Long value) throws NotEnoughBalanceException {
        from.deductBalance(value);
        to.addBalance(value);
    }
}
