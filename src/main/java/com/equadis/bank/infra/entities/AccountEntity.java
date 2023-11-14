package com.equadis.bank.infra.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

    @Id
    @SequenceGenerator(name = "sq_account", sequenceName = "sq_account", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_account")
    private Long id;

    private Long customerId;
    private Long amount;
    private Boolean active;
}
