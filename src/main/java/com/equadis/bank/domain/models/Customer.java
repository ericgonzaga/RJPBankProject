package com.equadis.bank.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Customer {

    private Long id;
    private String name;
    private Boolean active;

    public Customer() {
        this.active = true;
    }

    public Customer(String name) {
        this();
        this.name = name;
    }
}
