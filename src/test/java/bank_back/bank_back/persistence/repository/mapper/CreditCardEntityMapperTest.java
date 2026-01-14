package bank_back.bank_back.persistence.repository.mapper;

import bank_back.bank_back.domain.model.BankAccount;
import bank_back.bank_back.domain.model.CreditCard;
import bank_back.bank_back.persistence.dao.jpa.entity.BankAccountJpaEntity;
import bank_back.bank_back.persistence.dao.jpa.entity.CreditCardJpaEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardEntityMapperTest {

    private final CreditCardEntityMapper mapper = CreditCardEntityMapper.getInstance();

    @Test
    void toEntity_ShouldMapFields_WhenModelIsNotNull() {
        BankAccount account = new BankAccount();
        account.setId(1L);
        account.setBalance(new BigDecimal("100.00"));
        account.setIban("ES1234567890");

        CreditCard model = new CreditCard();
        model.setId(2L);
        model.setNumber("4111111111111111");
        model.setExpirationDate(LocalDate.of(2031, 12, 31));
        model.setCvv(321);
        model.setName("Marta");
        model.setBankAccount(account);

        CreditCardJpaEntity entity = mapper.toEntity(model);

        assertNotNull(entity);
        assertEquals(model.getId(), entity.getId());
        assertEquals(model.getNumber(), entity.getNumber());
        assertEquals(model.getExpirationDate(), entity.getExpirationDate());
        assertEquals(model.getCvv(), entity.getCvv());
        assertEquals(model.getName(), entity.getName());

        assertNotNull(entity.getBankAccount());
        assertEquals(account.getId(), entity.getBankAccount().getId());
        assertNull(entity.getBankAccount().getClient());
    }

    @Test
    void toEntity_ShouldReturnNull_WhenModelIsNull() {
        assertNull(mapper.toEntity(null));
    }

    @Test
    void toModel_ShouldMapFields_WhenEntityIsNotNull() {
        BankAccountJpaEntity account = new BankAccountJpaEntity();
        account.setId(4L);
        account.setBalance(new BigDecimal("200.00"));
        account.setIban("ES0987654321");

        CreditCardJpaEntity entity = new CreditCardJpaEntity();
        entity.setId(5L);
        entity.setNumber("5555444433332222");
        entity.setExpirationDate(LocalDate.of(2032, 11, 30));
        entity.setCvv(123);
        entity.setName("Luis");
        entity.setBankAccount(account);

        CreditCard model = mapper.toModel(entity);

        assertNotNull(model);
        assertEquals(entity.getId(), model.getId());
        assertEquals(entity.getNumber(), model.getNumber());
        assertEquals(entity.getExpirationDate(), model.getExpirationDate());
        assertEquals(entity.getCvv(), model.getCvv());
        assertEquals(entity.getName(), model.getName());

        assertNotNull(model.getBankAccount());
        assertEquals(account.getId(), model.getBankAccount().getId());
        assertNull(model.getBankAccount().getClient());
    }

    @Test
    void toModel_ShouldReturnNull_WhenEntityIsNull() {
        assertNull(mapper.toModel(null));
    }

    @Test
    void toEntityWithoutBankAccount_ShouldMapFields_WhenModelIsNotNull() {
        CreditCard model = new CreditCard();
        model.setId(6L);
        model.setNumber("4444333322221111");
        model.setExpirationDate(LocalDate.of(2033, 10, 31));
        model.setCvv(999);
        model.setName("Ana");

        CreditCardJpaEntity entity = mapper.toEntityWithoutBankAccount(model);

        assertNotNull(entity);
        assertEquals(model.getId(), entity.getId());
        assertEquals(model.getNumber(), entity.getNumber());
        assertEquals(model.getExpirationDate(), entity.getExpirationDate());
        assertEquals(model.getCvv(), entity.getCvv());
        assertEquals(model.getName(), entity.getName());
        assertNull(entity.getBankAccount());
    }

    @Test
    void toModelWithoutBankAccount_ShouldMapFields_WhenEntityIsNotNull() {
        CreditCardJpaEntity entity = new CreditCardJpaEntity();
        entity.setId(7L);
        entity.setNumber("1234123412341234");
        entity.setExpirationDate(LocalDate.of(2034, 9, 30));
        entity.setCvv(555);
        entity.setName("Ana");

        CreditCard model = mapper.toModelWithoutBankAccount(entity);

        assertNotNull(model);
        assertEquals(entity.getId(), model.getId());
        assertEquals(entity.getNumber(), model.getNumber());
        assertEquals(entity.getExpirationDate(), model.getExpirationDate());
        assertEquals(entity.getCvv(), model.getCvv());
        assertEquals(entity.getName(), model.getName());
        assertNull(model.getBankAccount());
    }
}
