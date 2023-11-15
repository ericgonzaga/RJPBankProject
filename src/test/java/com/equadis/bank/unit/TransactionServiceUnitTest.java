package com.equadis.bank.unit;

import static org.junit.Assert.assertEquals;

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
public class TransactionServiceUnitTest extends BaseUnitTest {

    @InjectMocks
    private TransactionService service;

    @Mock
    private ITransactionRepository repository;

    @Mock
    private AccountService accountService;

    @Mock
    private IAccountRepository accountRepository;

    Account accountDeposit, accountWithdraw, accountFrom, accountTo;

    @BeforeAll
    public void beforeAll() {
        this.accountDeposit = new Account(1L, 1L, 1000L, true);
        this.accountWithdraw = new Account(2L, 1L, 1000L, true);
        this.accountFrom = new Account(3L, 1L, 1000L, true);
        this.accountTo = new Account(4L, 1L, 1000L, true);

        this.accountService = Mockito.mock(AccountService.class);
        this.accountRepository = Mockito.mock(IAccountRepository.class);
        this.repository = Mockito.mock(ITransactionRepository.class);
    }

    @Test
    public void testDeposit() {
        Mockito.doReturn(accountDeposit).when(this.accountService).get(accountDeposit.getId());

        this.service.deposit(accountDeposit.getId(), 100L);

        assertEquals(1100L, accountDeposit.getBalance().longValue());
        Mockito.verify(this.accountService, Mockito.times(1)).get(Mockito.anyLong());
        Mockito.verify(this.accountRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(this.repository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void testWithdrawHasBalance() throws NotEnoughBalanceException {
        Mockito.doReturn(accountWithdraw).when(this.accountService).get(accountWithdraw.getId());

        this.service.withdraw(accountWithdraw.getId(), 100L);

        assertEquals(900L, accountWithdraw.getBalance().longValue());
        Mockito.verify(this.accountService, Mockito.times(1)).get(Mockito.anyLong());
        Mockito.verify(this.accountRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(this.repository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void testTransferHasBalance() throws NotEnoughBalanceException {
        Mockito.doReturn(accountFrom).when(this.accountService).get(accountFrom.getId());
        Mockito.doReturn(accountTo).when(this.accountService).get(accountTo.getId());

        this.service.transfer(accountFrom.getId(), accountTo.getId(), 100L);

        assertEquals(900L, accountFrom.getBalance().longValue());
        assertEquals(1100L, accountTo.getBalance().longValue());
        Mockito.verify(this.accountService, Mockito.times(2)).get(Mockito.anyLong());
        Mockito.verify(this.accountRepository, Mockito.times(2)).save(Mockito.any());
        Mockito.verify(this.repository, Mockito.times(2)).save(Mockito.any());
    }
}
