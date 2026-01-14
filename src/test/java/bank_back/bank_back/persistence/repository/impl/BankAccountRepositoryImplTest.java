package bank_back.bank_back.persistence.repository.impl;

import bank_back.bank_back.domain.model.BankAccount;
import bank_back.bank_back.persistence.dao.jpa.BankAccountJpaDao;
import bank_back.bank_back.persistence.dao.jpa.entity.BankAccountJpaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BankAccountRepositoryImplTest {

    private BankAccountJpaDao bankAccountJpaDao;

    private BankAccountRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        bankAccountJpaDao = mock(BankAccountJpaDao.class);
        repository = new BankAccountRepositoryImpl(bankAccountJpaDao);
    }

    @Test
    void findAll_ShouldMapEntities() {
        BankAccountJpaEntity entity = new BankAccountJpaEntity();
        entity.setId(1L);
        entity.setBalance(new BigDecimal("100.00"));
        entity.setIban("ES123");

        when(bankAccountJpaDao.findAll(0, Integer.MAX_VALUE)).thenReturn(List.of(entity));

        List<BankAccount> result = repository.findAll();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(entity.getBalance(), result.get(0).getBalance());
        verify(bankAccountJpaDao).findAll(0, Integer.MAX_VALUE);
    }

    @Test
    void findById_ShouldMapEntity_WhenExists() {
        BankAccountJpaEntity entity = new BankAccountJpaEntity();
        entity.setId(2L);
        entity.setBalance(new BigDecimal("200.00"));
        entity.setIban("ES456");

        when(bankAccountJpaDao.findById(2L)).thenReturn(Optional.of(entity));

        Optional<BankAccount> result = repository.findById(2L);

        assertTrue(result.isPresent());
        assertEquals(2L, result.get().getId());
        assertEquals(entity.getIban(), result.get().getIban());
    }

    @Test
    void findById_ShouldReturnEmpty_WhenMissing() {
        when(bankAccountJpaDao.findById(99L)).thenReturn(Optional.empty());

        Optional<BankAccount> result = repository.findById(99L);

        assertTrue(result.isEmpty());
    }

    @Test
    void save_ShouldInsert_WhenIdIsNull() {
        BankAccount model = new BankAccount();
        model.setBalance(new BigDecimal("300.00"));
        model.setIban("ES789");

        BankAccountJpaEntity savedEntity = new BankAccountJpaEntity();
        savedEntity.setId(3L);
        savedEntity.setBalance(model.getBalance());
        savedEntity.setIban(model.getIban());

        when(bankAccountJpaDao.insert(any(BankAccountJpaEntity.class))).thenReturn(savedEntity);

        BankAccount result = repository.save(model);

        ArgumentCaptor<BankAccountJpaEntity> captor = ArgumentCaptor.forClass(BankAccountJpaEntity.class);
        verify(bankAccountJpaDao).insert(captor.capture());
        verify(bankAccountJpaDao, never()).update(any(BankAccountJpaEntity.class));

        BankAccountJpaEntity captured = captor.getValue();
        assertNull(captured.getId());
        assertEquals(model.getBalance(), captured.getBalance());
        assertEquals(model.getIban(), captured.getIban());

        assertEquals(3L, result.getId());
        assertEquals(model.getIban(), result.getIban());
    }

    @Test
    void save_ShouldUpdate_WhenIdIsNotNull() {
        BankAccount model = new BankAccount();
        model.setId(4L);
        model.setBalance(new BigDecimal("400.00"));
        model.setIban("ES321");

        BankAccountJpaEntity updatedEntity = new BankAccountJpaEntity();
        updatedEntity.setId(4L);
        updatedEntity.setBalance(model.getBalance());
        updatedEntity.setIban(model.getIban());

        when(bankAccountJpaDao.update(any(BankAccountJpaEntity.class))).thenReturn(updatedEntity);

        BankAccount result = repository.save(model);

        verify(bankAccountJpaDao).update(any(BankAccountJpaEntity.class));
        verify(bankAccountJpaDao, never()).insert(any(BankAccountJpaEntity.class));
        assertEquals(4L, result.getId());
        assertEquals(model.getBalance(), result.getBalance());
    }

    @Test
    void deleteById_ShouldDelegateToDao() {
        repository.deleteById(5L);

        verify(bankAccountJpaDao).deleteById(5L);
    }
}
