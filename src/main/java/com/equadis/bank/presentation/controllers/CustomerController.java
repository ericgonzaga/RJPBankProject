package com.equadis.bank.presentation.controllers;

import java.util.List;

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

import com.equadis.bank.domain.entities.customer.Customer;
import com.equadis.bank.domain.entities.customer.ICustomerService;
import com.equadis.bank.presentation.viewmodels.NewCustomerModel;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "customer")
@AllArgsConstructor
public class CustomerController {
    private final ICustomerService service;

    @ResponseBody
    @GetMapping(path = "/{id}")
    public ResponseEntity<Customer> get(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.get(id));
    }

    @ResponseBody
    @GetMapping(path = "/list")
    public ResponseEntity<List<Customer>> list() {
        return ResponseEntity.ok(this.service.list());
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody NewCustomerModel model) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.create(model.getName()));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }
}