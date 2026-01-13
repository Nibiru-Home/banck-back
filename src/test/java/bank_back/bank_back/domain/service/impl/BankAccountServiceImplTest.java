package bank_back.bank_back.domain.service.impl;

import bank_back.bank_back.domain.model.BankAccount;
import bank_back.bank_back.domain.repository.BankAccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BankAccountServiceImplTest {

    @Mock
    private BankAccountRepository bankAccountRepository;

    @InjectMocks
    private BankAccountServiceImpl bankAccountService;

    @Test
    void findAll_ShouldReturnList() {
        BankAccount account = new BankAccount();
        account.setId(1L);
        when(bankAccountRepository.findAll()).thenReturn(Collections.singletonList(account));

        List<BankAccount> result = bankAccountService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(bankAccountRepository).findAll();
    }

    @Test
    void findById_ShouldReturnAccount_WhenExists() {
        BankAccount account = new BankAccount();
        account.setId(1L);
        when(bankAccountRepository.findById(1L)).thenReturn(Optional.of(account));

        BankAccount result = bankAccountService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(bankAccountRepository).findById(1L);
    }

    @Test
    void findById_ShouldReturnNull_WhenDoesNotExist() {
        when(bankAccountRepository.findById(1L)).thenReturn(Optional.empty());

        BankAccount result = bankAccountService.findById(1L);

        assertNull(result);
        verify(bankAccountRepository).findById(1L);
    }

    @Test
    void create_ShouldReturnSavedAccount() {
        BankAccount account = new BankAccount();
        account.setId(1L);
        when(bankAccountRepository.save(account)).thenReturn(account);

        BankAccount result = bankAccountService.create(account);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(bankAccountRepository).save(account);
    }

    @Test
    void update_ShouldReturnUpdatedAccount_WhenExists() {
        BankAccount account = new BankAccount();
        account.setId(1L);

        when(bankAccountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(bankAccountRepository.save(account)).thenReturn(account);

        BankAccount result = bankAccountService.update(1L, account);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(bankAccountRepository).findById(1L);
        verify(bankAccountRepository).save(account);
    }

    @Test
    void update_ShouldReturnNull_WhenDoesNotExist() {
        BankAccount account = new BankAccount();
        account.setId(1L);
        when(bankAccountRepository.findById(1L)).thenReturn(Optional.empty());

        BankAccount result = bankAccountService.update(1L, account);

        assertNull(result);
        verify(bankAccountRepository).findById(1L);
        verify(bankAccountRepository, never()).save(account);
    }

    @Test
    void delete_ShouldCallRepositoryDelete() {
        bankAccountService.delete(1L);

        verify(bankAccountRepository).deleteById(1L);
    }
}
