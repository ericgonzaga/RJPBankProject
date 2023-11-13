package com.equadis.bank.presentation.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.equadis.bank.domain.errors.NotEnoughBalanceException;
import com.equadis.bank.domain.models.Transaction;
import com.equadis.bank.domain.usecases.ITransactionService;
import com.equadis.bank.presentation.viewmodels.TransactionViewModel;
import com.equadis.bank.presentation.viewmodels.TransferTransactionViewModel;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "transaction")
@AllArgsConstructor
public class TransactionController {

    private final ITransactionService service;

    @ResponseBody
    @GetMapping(path = "/listByAccount/{accountId}")
    public ResponseEntity<List<Transaction>> listByAccount(Long accountId) {
        return ResponseEntity.ok(this.service.listByAccount(accountId));
    }

    @PutMapping(path = "/deposit")
    public ResponseEntity<Object> deposit(@RequestBody TransactionViewModel transaction) {
        this.service.deposit(transaction.getAccountId(), transaction.getValue());
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/withdraw")
    public ResponseEntity<Object> withdraw(@RequestBody TransactionViewModel transaction)
            throws NotEnoughBalanceException {
        this.service.withdraw(transaction.getAccountId(), transaction.getValue());
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/transfer")
    public ResponseEntity<Object> transfer(@RequestBody TransferTransactionViewModel transaction)
            throws NotEnoughBalanceException {
        this.service.transfer(transaction.getAccountIdFrom(), transaction.getAccountIdTo(), transaction.getValue());
        return ResponseEntity.ok().build();
    }
}
