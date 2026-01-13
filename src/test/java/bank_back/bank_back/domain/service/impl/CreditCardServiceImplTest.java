package bank_back.bank_back.domain.service.impl;

import bank_back.bank_back.domain.model.CreditCard;
import bank_back.bank_back.domain.repository.CreditCardRepository;
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
class CreditCardServiceImplTest {

    @Mock
    private CreditCardRepository creditCardRepository;

    @InjectMocks
    private CreditCardServiceImpl creditCardService;

    @Test
    void findAll_ShouldReturnList() {
        CreditCard card = new CreditCard();
        card.setId(1L);
        when(creditCardRepository.findAll()).thenReturn(Collections.singletonList(card));

        List<CreditCard> result = creditCardService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(creditCardRepository).findAll();
    }

    @Test
    void findById_ShouldReturnCard_WhenExists() {
        CreditCard card = new CreditCard();
        card.setId(1L);
        when(creditCardRepository.findById(1L)).thenReturn(Optional.of(card));

        CreditCard result = creditCardService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(creditCardRepository).findById(1L);
    }

    @Test
    void findById_ShouldReturnNull_WhenDoesNotExist() {
        when(creditCardRepository.findById(1L)).thenReturn(Optional.empty());

        CreditCard result = creditCardService.findById(1L);

        assertNull(result);
        verify(creditCardRepository).findById(1L);
    }

    @Test
    void create_ShouldReturnSavedCard() {
        CreditCard card = new CreditCard();
        card.setId(1L);
        when(creditCardRepository.save(card)).thenReturn(card);

        CreditCard result = creditCardService.create(card);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(creditCardRepository).save(card);
    }

    @Test
    void update_ShouldReturnUpdatedCard_WhenExists() {
        CreditCard card = new CreditCard();
        card.setId(1L);

        when(creditCardRepository.findById(1L)).thenReturn(Optional.of(card));
        when(creditCardRepository.save(card)).thenReturn(card);

        CreditCard result = creditCardService.update(1L, card);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(creditCardRepository).findById(1L);
        verify(creditCardRepository).save(card);
    }

    @Test
    void update_ShouldReturnNull_WhenDoesNotExist() {
        CreditCard card = new CreditCard();
        card.setId(1L);
        when(creditCardRepository.findById(1L)).thenReturn(Optional.empty());

        CreditCard result = creditCardService.update(1L, card);

        assertNull(result);
        verify(creditCardRepository).findById(1L);
        verify(creditCardRepository, never()).save(card);
    }

    @Test
    void delete_ShouldCallRepositoryDelete() {
        creditCardService.delete(1L);

        verify(creditCardRepository).deleteById(1L);
    }
}
