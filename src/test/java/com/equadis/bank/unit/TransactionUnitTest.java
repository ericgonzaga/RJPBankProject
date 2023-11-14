package com.equadis.bank.unit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.equadis.bank.domain.errors.NotEnoughBalanceException;
import com.equadis.bank.domain.models.Account;
import com.equadis.bank.infra.repositories.IAccountRepository;
import com.equadis.bank.infra.repositories.ITransactionRepository;
import com.equadis.bank.services.AccountService;
import com.equadis.bank.services.TransactionService;

@ExtendWith(MockitoExtension.class)
public class TransactionUnitTest extends BaseUnitTest {

    @InjectMocks
    private TransactionService service;

    @Mock
    private ITransactionRepository repository;

    @Mock
    private AccountService accountService;

    @Mock
    private IAccountRepository accountRepository;

    private Account account1;
    private Account account2;

    @BeforeAll
    public void setup() {
        this.account1 = new Account(1L, 1L, 1000L, true);
        this.account2 = new Account(2L, 2L, 1000L, true);

        this.accountService = Mockito.mock(AccountService.class);
        this.accountRepository = Mockito.mock(IAccountRepository.class);
        this.repository = Mockito.mock(ITransactionRepository.class);
    }

    @Test
    public void testDeposit() {
        Mockito.doReturn(this.account1).when(this.accountService).get(this.account1.getId());
        this.service.deposit(this.account1.getId(), 100L);

        Mockito.verify(this.accountService, Mockito.times(1)).get(Mockito.anyLong());
        Mockito.verify(this.accountRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(this.repository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void testWithdrawHasBalance() throws NotEnoughBalanceException {
        Mockito.doReturn(this.account1).when(this.accountService).get(this.account1.getId());
        this.service.withdraw(this.account1.getId(), 100L);

        Mockito.verify(this.accountService, Mockito.times(1)).get(Mockito.anyLong());
        Mockito.verify(this.accountRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(this.repository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void testTransferHasBalance() throws NotEnoughBalanceException {
        Mockito.doReturn(this.account1).when(this.accountService).get(this.account1.getId());
        Mockito.doReturn(this.account2).when(this.accountService).get(this.account2.getId());

        this.service.transfer(this.account1.getId(), this.account2.getId(), 100L);

        Mockito.verify(this.accountService, Mockito.times(2)).get(Mockito.anyLong());
        Mockito.verify(this.accountRepository, Mockito.times(2)).save(Mockito.any());
        Mockito.verify(this.repository, Mockito.times(2)).save(Mockito.any());
    }
}
