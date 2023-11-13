package com.equadis.bank.domain.models;

import java.time.LocalDateTime;

import com.equadis.bank.domain.types.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private Long id;
    private Long accountId;
    private TransactionType type;
    private LocalDateTime date;
    private Long value;

    public Transaction(Long accountId, TransactionType type, Long value) {
        this.accountId = accountId;
        this.type = type;
        this.value = value;
        this.date = LocalDateTime.now();
    }
}