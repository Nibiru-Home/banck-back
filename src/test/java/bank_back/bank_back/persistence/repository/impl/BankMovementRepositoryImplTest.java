package bank_back.bank_back.persistence.repository.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import bank_back.bank_back.domain.model.BankMovement;
import bank_back.bank_back.domain.model.MovementOrigin;
import bank_back.bank_back.domain.model.MovementType;
import bank_back.bank_back.persistence.dao.jpa.BankMovementJpaDao;
import bank_back.bank_back.persistence.dao.jpa.entity.BankMovementJpaEntity;

@ExtendWith(MockitoExtension.class)
class BankMovementRepositoryImplTest {

    @Mock
    private BankMovementJpaDao bankMovementJpaDao;

    private BankMovementRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        repository = new BankMovementRepositoryImpl(bankMovementJpaDao);
    }

    @Test
    void findAll_ShouldMapEntities() {
        BankMovementJpaEntity entity = new BankMovementJpaEntity();
        entity.setId(1L);
        entity.setMovementType(MovementType.Add);
        entity.setMovementOrigin(MovementOrigin.Transfer);
        entity.setTimestamp(LocalDateTime.of(2024, 1, 1, 8, 0));
        entity.setAmount(new BigDecimal("50.00"));
        entity.setConcept("Test");

        when(bankMovementJpaDao.findAll(0, Integer.MAX_VALUE)).thenReturn(List.of(entity));

        List<BankMovement> result = repository.findAll();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(entity.getAmount(), result.get(0).getAmount());
        verify(bankMovementJpaDao).findAll(0, Integer.MAX_VALUE);
    }

    @Test
    void findById_ShouldMapEntity_WhenExists() {
        BankMovementJpaEntity entity = new BankMovementJpaEntity();
        entity.setId(2L);
        entity.setMovementType(MovementType.Remove);
        entity.setMovementOrigin(MovementOrigin.Bank_card);

        when(bankMovementJpaDao.findById(2L)).thenReturn(Optional.of(entity));

        Optional<BankMovement> result = repository.findById(2L);

        assertTrue(result.isPresent());
        assertEquals(2L, result.get().getId());
        assertEquals(entity.getMovementType(), result.get().getMovementType());
    }

    @Test
    void findById_ShouldReturnEmpty_WhenMissing() {
        when(bankMovementJpaDao.findById(99L)).thenReturn(Optional.empty());

        Optional<BankMovement> result = repository.findById(99L);

        assertTrue(result.isEmpty());
    }

    @Test
    void save_ShouldInsert_WhenIdIsNull() {
        BankMovement model = new BankMovement();
        model.setMovementType(MovementType.Add);
        model.setMovementOrigin(MovementOrigin.Transfer);
        model.setTimestamp(LocalDateTime.of(2024, 1, 2, 10, 0));
        model.setAmount(new BigDecimal("20.00"));
        model.setConcept("Concept");

        BankMovementJpaEntity savedEntity = new BankMovementJpaEntity();
        savedEntity.setId(3L);
        savedEntity.setMovementType(model.getMovementType());
        savedEntity.setMovementOrigin(model.getMovementOrigin());
        savedEntity.setAmount(model.getAmount());
        savedEntity.setConcept(model.getConcept());

        when(bankMovementJpaDao.insert(any(BankMovementJpaEntity.class))).thenReturn(savedEntity);

        BankMovement result = repository.save(model);

        ArgumentCaptor<BankMovementJpaEntity> captor = ArgumentCaptor.forClass(BankMovementJpaEntity.class);
        verify(bankMovementJpaDao).insert(captor.capture());
        verify(bankMovementJpaDao, never()).update(any(BankMovementJpaEntity.class));

        BankMovementJpaEntity captured = captor.getValue();
        assertNull(captured.getId());
        assertEquals(model.getMovementType(), captured.getMovementType());
        assertEquals(model.getAmount(), captured.getAmount());

        assertEquals(3L, result.getId());
        assertEquals(model.getConcept(), result.getConcept());
    }

    @Test
    void save_ShouldUpdate_WhenIdIsNotNull() {
        BankMovement model = new BankMovement();
        model.setId(4L);
        model.setMovementType(MovementType.Remove);
        model.setMovementOrigin(MovementOrigin.Direct_debit);
        model.setAmount(new BigDecimal("15.00"));
        model.setConcept("Charge");

        BankMovementJpaEntity updatedEntity = new BankMovementJpaEntity();
        updatedEntity.setId(4L);
        updatedEntity.setMovementType(model.getMovementType());
        updatedEntity.setMovementOrigin(model.getMovementOrigin());
        updatedEntity.setAmount(model.getAmount());
        updatedEntity.setConcept(model.getConcept());

        when(bankMovementJpaDao.update(any(BankMovementJpaEntity.class))).thenReturn(updatedEntity);

        BankMovement result = repository.save(model);

        verify(bankMovementJpaDao).update(any(BankMovementJpaEntity.class));
        verify(bankMovementJpaDao, never()).insert(any(BankMovementJpaEntity.class));
        assertEquals(4L, result.getId());
        assertEquals(model.getMovementOrigin(), result.getMovementOrigin());
    }

    @Test
    void deleteById_ShouldDelegateToDao() {
        repository.deleteById(5L);

        verify(bankMovementJpaDao).deleteById(5L);
    }
}
