package com.equadis.bank.domain.errors;

public class NotEnoughBalanceException extends Exception {

    public NotEnoughBalanceException() {
        super("Not enough balance.");
    }
}