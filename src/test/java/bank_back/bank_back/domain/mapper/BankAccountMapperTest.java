package bank_back.bank_back.domain.mapper;

import bank_back.bank_back.domain.dto.BankAccountDto;
import bank_back.bank_back.domain.dto.BankMovementDto;
import bank_back.bank_back.domain.dto.CreditCardDto;
import bank_back.bank_back.domain.model.BankAccount;
import bank_back.bank_back.domain.model.BankMovement;
import bank_back.bank_back.domain.model.Client;
import bank_back.bank_back.domain.model.CreditCard;
import bank_back.bank_back.domain.model.MovementOrigin;
import bank_back.bank_back.domain.model.MovementType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountMapperTest {

    private final BankAccountMapper mapper = BankAccountMapper.getInstance();

    @Test
    void toDto_ShouldMapAllFields_WhenModelIsNotNull() {
        Client client = new Client();
        client.setId(UUID.randomUUID());
        client.setLogin("testuser");
        client.setBankAccounts(null);

        BankMovement movement = new BankMovement();
        movement.setId(10L);
        movement.setAmount(new BigDecimal("50.00"));

        CreditCard creditCard = new CreditCard();
        creditCard.setId(20L);
        creditCard.setNumber("1111222233334444");

        BankAccount model = new BankAccount();
        model.setBalance(new BigDecimal("1000.00"));
        model.setIban("ES1234567890");
        model.setClient(client);

        List<BankMovement> movements = new ArrayList<>();
        movements.add(movement);
        model.setMovements(movements);

        List<CreditCard> creditCards = new ArrayList<>();
        creditCards.add(creditCard);
        model.setCreditCards(creditCards);

        BankAccountDto dto = mapper.toDto(model);

        assertNotNull(dto);
        assertNull(dto.id());
        assertEquals(model.getBalance(), dto.balance());
        assertEquals(model.getIban(), dto.iban());

        assertNotNull(dto.client());
        assertEquals(client.getId(), dto.client().id());

        assertNotNull(dto.movements());
        assertEquals(1, dto.movements().size());
        assertEquals(movement.getId(), dto.movements().get(0).id());

        assertNotNull(dto.creditCards());
        assertEquals(1, dto.creditCards().size());
        assertEquals(creditCard.getId(), dto.creditCards().get(0).id());
    }

    @Test
    void toDto_ShouldHandleNullCollections_WhenModelHasNullLists() {
        BankAccount model = new BankAccount();
        model.setBalance(new BigDecimal("1000.00"));
        model.setIban("ES1234567890");
        model.setClient(null);
        model.setMovements(null);
        model.setCreditCards(null);

        BankAccountDto dto = mapper.toDto(model);

        assertNotNull(dto);
        assertNull(dto.client());
        assertNull(dto.movements());
        assertNull(dto.creditCards());
    }

    @Test
    void toDto_ShouldReturnNull_WhenModelIsNull() {
        assertNull(mapper.toDto(null));
    }

    @Test
    void toModel_ShouldMapAllFields_WhenDtoIsNotNull() {
        BankMovementDto movementDto = new BankMovementDto(
                10L, new BigDecimal("50.00"), MovementType.Remove, MovementOrigin.Bank_card, "Test", null, null, null);

        CreditCardDto creditCardDto = new CreditCardDto(
                20L, "1111222233334444", null, 123, "Name");

        BankAccountDto dto = new BankAccountDto(
                1L,
                new BigDecimal("1000.00"),
                "ES1234567890",
                null,
                List.of(movementDto),
                List.of(creditCardDto));

        BankAccount model = mapper.toModel(dto);

        assertNotNull(model);
        assertNull(model.getId());

        assertEquals(dto.balance(), model.getBalance());
        assertEquals(dto.iban(), model.getIban());

        assertNull(model.getClient());

        assertNotNull(model.getMovements());
        assertEquals(1, model.getMovements().size());
        assertNull(model.getMovements().get(0).getId());
        assertEquals(movementDto.amount(), model.getMovements().get(0).getAmount());

        assertNotNull(model.getCreditCards());
        assertEquals(1, model.getCreditCards().size());
        assertEquals(creditCardDto.id(), model.getCreditCards().get(0).getId());
    }

    @Test
    void toModel_ShouldReturnNull_WhenDtoIsNull() {
        assertNull(mapper.toModel(null));
    }
}
