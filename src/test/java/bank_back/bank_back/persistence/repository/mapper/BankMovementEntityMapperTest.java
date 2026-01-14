package bank_back.bank_back.persistence.repository.mapper;

import bank_back.bank_back.domain.model.BankAccount;
import bank_back.bank_back.domain.model.BankMovement;
import bank_back.bank_back.domain.model.CreditCard;
import bank_back.bank_back.domain.model.MovementOrigin;
import bank_back.bank_back.domain.model.MovementType;
import bank_back.bank_back.persistence.dao.jpa.entity.BankAccountJpaEntity;
import bank_back.bank_back.persistence.dao.jpa.entity.BankMovementJpaEntity;
import bank_back.bank_back.persistence.dao.jpa.entity.CreditCardJpaEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BankMovementEntityMapperTest {

    private final BankMovementEntityMapper mapper = BankMovementEntityMapper.getInstance();

    @Test
    void toEntity_ShouldMapFields_WhenModelIsNotNull() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(2L);
        bankAccount.setBalance(new BigDecimal("300.00"));
        bankAccount.setIban("ES9999999999");

        CreditCard creditCard = new CreditCard();
        creditCard.setId(7L);
        creditCard.setNumber("5555666677778888");
        creditCard.setExpirationDate(LocalDate.of(2032, 5, 31));
        creditCard.setCvv(555);
        creditCard.setName("Ana Perez");

        BankMovement model = new BankMovement();
        model.setId(1L);
        model.setMovementType(MovementType.Add);
        model.setMovementOrigin(MovementOrigin.Transfer);
        model.setOriginCreditCard(creditCard);
        model.setTimestamp(LocalDateTime.of(2024, 3, 10, 9, 0));
        model.setAmount(new BigDecimal("75.00"));
        model.setConcept("Test movement");
        model.setBankAccount(bankAccount);

        BankMovementJpaEntity entity = mapper.toEntity(model);

        assertNotNull(entity);
        assertEquals(model.getId(), entity.getId());
        assertEquals(model.getMovementType(), entity.getMovementType());
        assertEquals(model.getMovementOrigin(), entity.getMovementOrigin());
        assertEquals(model.getAmount(), entity.getAmount());
        assertEquals(model.getConcept(), entity.getConcept());

        assertNotNull(entity.getOriginCreditCard());
        assertEquals(creditCard.getId(), entity.getOriginCreditCard().getId());
        assertNull(entity.getOriginCreditCard().getBankAccount());

        assertNotNull(entity.getBankAccount());
        assertEquals(bankAccount.getId(), entity.getBankAccount().getId());
        assertNull(entity.getBankAccount().getClient());
    }

    @Test
    void toEntity_ShouldReturnNull_WhenModelIsNull() {
        assertNull(mapper.toEntity(null));
    }

    @Test
    void toModel_ShouldMapFields_WhenEntityIsNotNull() {
        BankAccountJpaEntity bankAccount = new BankAccountJpaEntity();
        bankAccount.setId(3L);
        bankAccount.setBalance(new BigDecimal("400.00"));
        bankAccount.setIban("ES8888888888");

        CreditCardJpaEntity creditCard = new CreditCardJpaEntity();
        creditCard.setId(9L);
        creditCard.setNumber("4444333322221111");
        creditCard.setExpirationDate(LocalDate.of(2033, 7, 31));
        creditCard.setCvv(111);
        creditCard.setName("Luis Garcia");

        BankMovementJpaEntity entity = new BankMovementJpaEntity();
        entity.setId(5L);
        entity.setMovementType(MovementType.Remove);
        entity.setMovementOrigin(MovementOrigin.Bank_card);
        entity.setOriginCreditCard(creditCard);
        entity.setTimestamp(LocalDateTime.of(2024, 4, 15, 14, 30));
        entity.setAmount(new BigDecimal("20.00"));
        entity.setConcept("Payment");
        entity.setBankAccount(bankAccount);

        BankMovement model = mapper.toModel(entity);

        assertNotNull(model);
        assertEquals(entity.getId(), model.getId());
        assertEquals(entity.getMovementType(), model.getMovementType());
        assertEquals(entity.getMovementOrigin(), model.getMovementOrigin());
        assertEquals(entity.getAmount(), model.getAmount());
        assertEquals(entity.getConcept(), model.getConcept());

        assertNotNull(model.getOriginCreditCard());
        assertEquals(creditCard.getId(), model.getOriginCreditCard().getId());
        assertNull(model.getOriginCreditCard().getBankAccount());

        assertNotNull(model.getBankAccount());
        assertEquals(bankAccount.getId(), model.getBankAccount().getId());
        assertNull(model.getBankAccount().getClient());
    }

    @Test
    void toModel_ShouldReturnNull_WhenEntityIsNull() {
        assertNull(mapper.toModel(null));
    }

    @Test
    void toEntityWithoutBankAccount_ShouldMapFields_WhenModelIsNotNull() {
        BankMovement model = new BankMovement();
        model.setId(6L);
        model.setMovementType(MovementType.Add);
        model.setMovementOrigin(MovementOrigin.Direct_debit);
        model.setTimestamp(LocalDateTime.of(2024, 5, 20, 8, 0));
        model.setAmount(new BigDecimal("12.00"));
        model.setConcept("Charge");

        BankMovementJpaEntity entity = mapper.toEntityWithoutBankAccount(model);

        assertNotNull(entity);
        assertEquals(model.getId(), entity.getId());
        assertEquals(model.getMovementType(), entity.getMovementType());
        assertEquals(model.getMovementOrigin(), entity.getMovementOrigin());
        assertEquals(model.getAmount(), entity.getAmount());
        assertEquals(model.getConcept(), entity.getConcept());
        assertNull(entity.getBankAccount());
    }

    @Test
    void toModelWithoutBankAccount_ShouldMapFields_WhenEntityIsNotNull() {
        BankMovementJpaEntity entity = new BankMovementJpaEntity();
        entity.setId(8L);
        entity.setMovementType(MovementType.Remove);
        entity.setMovementOrigin(MovementOrigin.Transfer);
        entity.setTimestamp(LocalDateTime.of(2024, 6, 10, 16, 45));
        entity.setAmount(new BigDecimal("33.00"));
        entity.setConcept("Transfer");

        BankMovement model = mapper.toModelWithoutBankAccount(entity);

        assertNotNull(model);
        assertEquals(entity.getId(), model.getId());
        assertEquals(entity.getMovementType(), model.getMovementType());
        assertEquals(entity.getMovementOrigin(), model.getMovementOrigin());
        assertEquals(entity.getAmount(), model.getAmount());
        assertEquals(entity.getConcept(), model.getConcept());
        assertNull(model.getBankAccount());
    }
}
