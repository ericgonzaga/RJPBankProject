package com.equadis.bank.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.equadis.bank.domain.models.Customer;
import com.equadis.bank.presentation.controllers.AccountController;
import com.equadis.bank.presentation.viewmodels.NewAccountViewModel;

import jakarta.persistence.EntityManager;

@SpringBootTest
public class AccountIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private AccountController controller;

    @Autowired
    private EntityManager entityManager;

    private Customer customer;

    @BeforeAll
    public void setup() {
        this.customer = new Customer(1L, "Eric", true);
        this.entityManager.persist(this.customer);
    }

    @Test
    public void testCreateAccount() {
        NewAccountViewModel viewModel = new NewAccountViewModel();
        viewModel.setCustomerId(this.customer.getId());
        viewModel.setInitialAmount(1000L);

        ResponseEntity<Long> sut = this.controller.create(viewModel);

        assertNotNull(sut);
        assertEquals(HttpStatus.CREATED, sut.getStatusCode());
        assertNotNull(sut.getBody());
        assertEquals(Long.class, sut.getBody().getClass());
    }
}
