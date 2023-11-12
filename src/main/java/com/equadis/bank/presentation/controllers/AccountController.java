package com.equadis.bank.presentation.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.equadis.bank.domain.entities.account.Account;
import com.equadis.bank.domain.entities.account.IAccountService;
import com.equadis.bank.presentation.viewmodels.NewAccountModel;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "account")
@AllArgsConstructor
public class AccountController {

    private final IAccountService service;

    @ResponseBody
    @GetMapping(path = "/{id}")
    public ResponseEntity<Account> get(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.get(id));
    }

    @ResponseBody
    @GetMapping(path = "/getByCostumer/{customerId}")
    public ResponseEntity<Account> getByCostumer(@PathVariable Long customerId) {
        return ResponseEntity.ok(this.service.getByCustomer(customerId));
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody NewAccountModel model) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.service.create(model.getCustomerId(), model.getInitialAmount()));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }
}
