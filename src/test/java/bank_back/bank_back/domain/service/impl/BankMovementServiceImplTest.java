package bank_back.bank_back.domain.service.impl;

import bank_back.bank_back.domain.model.BankMovement;
import bank_back.bank_back.domain.repository.BankMovementRepository;
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
class BankMovementServiceImplTest {

    @Mock
    private BankMovementRepository bankMovementRepository;

    @InjectMocks
    private BankMovementServiceImpl bankMovementService;

    @Test
    void findAll_ShouldReturnList() {
        BankMovement movement = new BankMovement();
        movement.setId(1L);
        when(bankMovementRepository.findAll()).thenReturn(Collections.singletonList(movement));

        List<BankMovement> result = bankMovementService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(bankMovementRepository).findAll();
    }

    @Test
    void findById_ShouldReturnMovement_WhenExists() {
        BankMovement movement = new BankMovement();
        movement.setId(1L);
        when(bankMovementRepository.findById(1L)).thenReturn(Optional.of(movement));

        BankMovement result = bankMovementService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(bankMovementRepository).findById(1L);
    }

    @Test
    void findById_ShouldReturnNull_WhenDoesNotExist() {
        when(bankMovementRepository.findById(1L)).thenReturn(Optional.empty());

        BankMovement result = bankMovementService.findById(1L);

        assertNull(result);
        verify(bankMovementRepository).findById(1L);
    }

    @Test
    void create_ShouldReturnSavedMovement() {
        BankMovement movement = new BankMovement();
        movement.setId(1L);
        when(bankMovementRepository.save(movement)).thenReturn(movement);

        BankMovement result = bankMovementService.create(movement);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(bankMovementRepository).save(movement);
    }

    @Test
    void update_ShouldReturnUpdatedMovement_WhenExists() {
        BankMovement movement = new BankMovement();
        movement.setId(1L);

        when(bankMovementRepository.findById(1L)).thenReturn(Optional.of(movement));
        when(bankMovementRepository.save(movement)).thenReturn(movement);

        BankMovement result = bankMovementService.update(1L, movement);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(bankMovementRepository).findById(1L);
        verify(bankMovementRepository).save(movement);
    }

    @Test
    void update_ShouldReturnNull_WhenDoesNotExist() {
        BankMovement movement = new BankMovement();
        movement.setId(1L);
        when(bankMovementRepository.findById(1L)).thenReturn(Optional.empty());

        BankMovement result = bankMovementService.update(1L, movement);

        assertNull(result);
        verify(bankMovementRepository).findById(1L);
        verify(bankMovementRepository, never()).save(movement);
    }

    @Test
    void delete_ShouldCallRepositoryDelete() {
        bankMovementService.delete(1L);

        verify(bankMovementRepository).deleteById(1L);
    }
}
