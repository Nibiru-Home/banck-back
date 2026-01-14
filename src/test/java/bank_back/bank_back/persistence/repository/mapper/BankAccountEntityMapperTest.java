package bank_back.bank_back.persistence.repository.mapper;

import bank_back.bank_back.domain.model.BankAccount;
import bank_back.bank_back.domain.model.BankMovement;
import bank_back.bank_back.domain.model.Client;
import bank_back.bank_back.domain.model.CreditCard;
import bank_back.bank_back.domain.model.MovementOrigin;
import bank_back.bank_back.domain.model.MovementType;
import bank_back.bank_back.persistence.dao.jpa.entity.BankAccountJpaEntity;
import bank_back.bank_back.persistence.dao.jpa.entity.BankMovementJpaEntity;
import bank_back.bank_back.persistence.dao.jpa.entity.ClientJpaEntity;
import bank_back.bank_back.persistence.dao.jpa.entity.CreditCardJpaEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountEntityMapperTest {

    private final BankAccountEntityMapper mapper = BankAccountEntityMapper.getInstance();

    @Test
    void toEntity_ShouldMapFieldsAndRelations_WhenModelIsNotNull() {
        UUID clientId = UUID.randomUUID();
        Client client = new Client();
        client.setId(clientId);
        client.setLogin("marta");

        BankMovement movement = new BankMovement();
        movement.setId(10L);
        movement.setMovementType(MovementType.Add);
        movement.setMovementOrigin(MovementOrigin.Transfer);
        movement.setTimestamp(LocalDateTime.of(2024, 1, 1, 10, 0));
        movement.setAmount(new BigDecimal("50.00"));
        movement.setConcept("Concept");

        CreditCard creditCard = new CreditCard();
        creditCard.setId(20L);
        creditCard.setNumber("1111222233334444");
        creditCard.setExpirationDate(LocalDate.of(2030, 12, 31));
        creditCard.setCvv(123);
        creditCard.setName("Marta");

        BankAccount model = new BankAccount();
        model.setId(1L);
        model.setBalance(new BigDecimal("1000.00"));
        model.setIban("ES1234567890");
        model.setClient(client);
        model.setMovements(List.of(movement));
        model.setCreditCards(List.of(creditCard));

        BankAccountJpaEntity entity = mapper.toEntity(model);

        assertNotNull(entity);
        assertEquals(model.getId(), entity.getId());
        assertEquals(model.getBalance(), entity.getBalance());
        assertEquals(model.getIban(), entity.getIban());

        assertNotNull(entity.getClient());
        assertEquals(clientId, entity.getClient().getId());

        assertNotNull(entity.getMovements());
        assertEquals(1, entity.getMovements().size());
        BankMovementJpaEntity movementEntity = entity.getMovements().get(0);
        assertEquals(movement.getId(), movementEntity.getId());
        assertEquals(movement.getMovementType(), movementEntity.getMovementType());
        assertSame(entity, movementEntity.getBankAccount());

        assertNotNull(entity.getCreditCards());
        assertEquals(1, entity.getCreditCards().size());
        CreditCardJpaEntity creditCardEntity = entity.getCreditCards().get(0);
        assertEquals(creditCard.getId(), creditCardEntity.getId());
        assertSame(entity, creditCardEntity.getBankAccount());
    }

    @Test
    void toEntity_ShouldReturnNull_WhenModelIsNull() {
        assertNull(mapper.toEntity(null));
    }

    @Test
    void toModel_ShouldMapFields_WhenEntityIsNotNull() {
        UUID clientId = UUID.randomUUID();
        ClientJpaEntity client = new ClientJpaEntity();
        client.setId(clientId);
        client.setLogin("marta");

        BankMovementJpaEntity movement = new BankMovementJpaEntity();
        movement.setId(10L);
        movement.setMovementType(MovementType.Remove);
        movement.setMovementOrigin(MovementOrigin.Bank_card);
        movement.setTimestamp(LocalDateTime.of(2024, 2, 1, 12, 0));
        movement.setAmount(new BigDecimal("25.00"));
        movement.setConcept("Payment");

        CreditCardJpaEntity creditCard = new CreditCardJpaEntity();
        creditCard.setId(20L);
        creditCard.setNumber("4111111111111111");
        creditCard.setExpirationDate(LocalDate.of(2031, 1, 31));
        creditCard.setCvv(321);
        creditCard.setName("Marta");

        BankAccountJpaEntity entity = new BankAccountJpaEntity();
        entity.setId(1L);
        entity.setBalance(new BigDecimal("1500.00"));
        entity.setIban("ES1111111111");
        entity.setClient(client);

        movement.setBankAccount(entity);
        creditCard.setBankAccount(entity);

        entity.setMovements(List.of(movement));
        entity.setCreditCards(List.of(creditCard));

        BankAccount model = mapper.toModel(entity);

        assertNotNull(model);
        assertEquals(entity.getId(), model.getId());
        assertEquals(entity.getBalance(), model.getBalance());
        assertEquals(entity.getIban(), model.getIban());

        assertNotNull(model.getClient());
        assertEquals(clientId, model.getClient().getId());

        assertNotNull(model.getMovements());
        assertEquals(1, model.getMovements().size());
        assertNull(model.getMovements().get(0).getBankAccount());

        assertNotNull(model.getCreditCards());
        assertEquals(1, model.getCreditCards().size());
        assertNull(model.getCreditCards().get(0).getBankAccount());
    }

    @Test
    void toModel_ShouldReturnNull_WhenEntityIsNull() {
        assertNull(mapper.toModel(null));
    }

    @Test
    void toEntityWithoutClient_ShouldMapFields_WhenModelIsNotNull() {
        BankAccount model = new BankAccount();
        model.setId(5L);
        model.setBalance(new BigDecimal("250.00"));
        model.setIban("ES2222222222");

        BankAccountJpaEntity entity = mapper.toEntityWithoutClient(model);

        assertNotNull(entity);
        assertEquals(model.getId(), entity.getId());
        assertEquals(model.getBalance(), entity.getBalance());
        assertEquals(model.getIban(), entity.getIban());
        assertNull(entity.getClient());
    }

    @Test
    void toModelWithoutClient_ShouldMapFields_WhenEntityIsNotNull() {
        BankAccountJpaEntity entity = new BankAccountJpaEntity();
        entity.setId(9L);
        entity.setBalance(new BigDecimal("75.00"));
        entity.setIban("ES3333333333");

        BankAccount model = mapper.toModelWithoutClient(entity);

        assertNotNull(model);
        assertEquals(entity.getId(), model.getId());
        assertEquals(entity.getBalance(), model.getBalance());
        assertEquals(entity.getIban(), model.getIban());
        assertNull(model.getClient());
    }
}
