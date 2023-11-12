package com.equadis.bank.domain.entities.operation;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Operation {

    private Long customerId;
    private OperationType type;
    private LocalDateTime date;
    private Long value;

    public Operation(Long customerId, OperationType type, Long value) {
        this.customerId = customerId;
        this.type = type;
        this.value = value;
        this.date = LocalDateTime.now();
    }
}