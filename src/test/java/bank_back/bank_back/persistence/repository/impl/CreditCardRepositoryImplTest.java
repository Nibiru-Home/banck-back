package bank_back.bank_back.persistence.repository.impl;

import bank_back.bank_back.domain.model.CreditCard;
import bank_back.bank_back.persistence.dao.jpa.CreditCardJpaDao;
import bank_back.bank_back.persistence.dao.jpa.entity.CreditCardJpaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditCardRepositoryImplTest {

    @Mock
    private CreditCardJpaDao creditCardJpaDao;

    private CreditCardRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        repository = new CreditCardRepositoryImpl(creditCardJpaDao);
    }

    @Test
    void findAll_ShouldMapEntities() {
        CreditCardJpaEntity entity = new CreditCardJpaEntity();
        entity.setId(1L);
        entity.setNumber("4111111111111111");

        when(creditCardJpaDao.findAll(0, Integer.MAX_VALUE)).thenReturn(List.of(entity));

        List<CreditCard> result = repository.findAll();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(entity.getNumber(), result.get(0).getNumber());
        verify(creditCardJpaDao).findAll(0, Integer.MAX_VALUE);
    }

    @Test
    void findById_ShouldMapEntity_WhenExists() {
        CreditCardJpaEntity entity = new CreditCardJpaEntity();
        entity.setId(2L);
        entity.setNumber("5555444433332222");

        when(creditCardJpaDao.findById(2L)).thenReturn(Optional.of(entity));

        Optional<CreditCard> result = repository.findById(2L);

        assertTrue(result.isPresent());
        assertEquals(2L, result.get().getId());
        assertEquals(entity.getNumber(), result.get().getNumber());
    }

    @Test
    void findById_ShouldReturnEmpty_WhenMissing() {
        when(creditCardJpaDao.findById(99L)).thenReturn(Optional.empty());

        Optional<CreditCard> result = repository.findById(99L);

        assertTrue(result.isEmpty());
    }

    @Test
    void save_ShouldInsert_WhenIdIsNull() {
        CreditCard model = new CreditCard();
        model.setNumber("4444333322221111");
        model.setExpirationDate(LocalDate.of(2030, 12, 31));
        model.setCvv(123);
        model.setName("Marta");

        CreditCardJpaEntity savedEntity = new CreditCardJpaEntity();
        savedEntity.setId(3L);
        savedEntity.setNumber(model.getNumber());
        savedEntity.setExpirationDate(model.getExpirationDate());
        savedEntity.setCvv(model.getCvv());
        savedEntity.setName(model.getName());

        when(creditCardJpaDao.insert(any(CreditCardJpaEntity.class))).thenReturn(savedEntity);

        CreditCard result = repository.save(model);

        ArgumentCaptor<CreditCardJpaEntity> captor = ArgumentCaptor.forClass(CreditCardJpaEntity.class);
        verify(creditCardJpaDao).insert(captor.capture());
        verify(creditCardJpaDao, never()).update(any(CreditCardJpaEntity.class));

        CreditCardJpaEntity captured = captor.getValue();
        assertNull(captured.getId());
        assertEquals(model.getNumber(), captured.getNumber());
        assertEquals(model.getExpirationDate(), captured.getExpirationDate());

        assertEquals(3L, result.getId());
        assertEquals(model.getNumber(), result.getNumber());
    }

    @Test
    void save_ShouldUpdate_WhenIdIsNotNull() {
        CreditCard model = new CreditCard();
        model.setId(4L);
        model.setNumber("1234123412341234");
        model.setExpirationDate(LocalDate.of(2031, 11, 30));

        CreditCardJpaEntity updatedEntity = new CreditCardJpaEntity();
        updatedEntity.setId(4L);
        updatedEntity.setNumber(model.getNumber());
        updatedEntity.setExpirationDate(model.getExpirationDate());

        when(creditCardJpaDao.update(any(CreditCardJpaEntity.class))).thenReturn(updatedEntity);

        CreditCard result = repository.save(model);

        verify(creditCardJpaDao).update(any(CreditCardJpaEntity.class));
        verify(creditCardJpaDao, never()).insert(any(CreditCardJpaEntity.class));
        assertEquals(4L, result.getId());
        assertEquals(model.getNumber(), result.getNumber());
    }

    @Test
    void deleteById_ShouldDelegateToDao() {
        repository.deleteById(5L);

        verify(creditCardJpaDao).deleteById(5L);
    }
}
