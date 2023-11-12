package com.equadis.bank.presentation.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.equadis.bank.domain.entities.operation.IOperationService;
import com.equadis.bank.domain.entities.operation.Operation;
import com.equadis.bank.domain.errors.NotEnoughBalanceException;
import com.equadis.bank.presentation.viewmodels.OperationModel;
import com.equadis.bank.presentation.viewmodels.TransferOperationModel;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "operation")
@AllArgsConstructor
public class OperationController {

    private final IOperationService service;

    @ResponseBody
    @GetMapping(path = "/listByCostumer/{customerId}")
    public ResponseEntity<List<Operation>> listByCustomer(Long customerId) {
        return ResponseEntity.ok(this.service.listByCustomer(customerId));
    }

    @PutMapping(path = "/deposit")
    public ResponseEntity<Object> deposit(@RequestBody OperationModel operation) {
        this.service.deposit(operation.getCustomerId(), operation.getValue());
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/withdraw")
    public ResponseEntity<Object> withdraw(@RequestBody OperationModel operation) throws NotEnoughBalanceException {
        this.service.withdraw(operation.getCustomerId(), operation.getValue());
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/transfer")
    public ResponseEntity<Object> transfer(@RequestBody TransferOperationModel operation)
            throws NotEnoughBalanceException {
        this.service.transfer(operation.getCustomerIdFrom(), operation.getCustomerIdTo(), operation.getValue());
        return ResponseEntity.ok().build();
    }
}
