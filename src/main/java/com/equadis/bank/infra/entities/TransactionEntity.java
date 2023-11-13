package com.equadis.bank.infra.entities;

import java.time.LocalDateTime;

import com.equadis.bank.domain.types.TransactionType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transaction")
@Getter
@Setter
public class TransactionEntity {

    @Id
    @SequenceGenerator(name = "sq_transaction", sequenceName = "sq_transaction", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_transaction")
    private Long id;

    private Long accountId;
    private TransactionType type;
    private LocalDateTime date;
    private Long value;
}
