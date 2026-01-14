package bank_back.bank_back.persistence.repository.mapper;

import bank_back.bank_back.domain.model.BankAccount;
import bank_back.bank_back.domain.model.Client;
import bank_back.bank_back.persistence.dao.jpa.entity.BankAccountJpaEntity;
import bank_back.bank_back.persistence.dao.jpa.entity.ClientJpaEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ClientEntityMapperTest {

    private final ClientEntityMapper mapper = ClientEntityMapper.getInstance();

    @Test
    void toEntity_ShouldMapFields_WhenModelIsNotNull() {
        UUID clientId = UUID.randomUUID();
        Client client = new Client();
        client.setId(clientId);
        client.setLogin("marta");
        client.setPassword("pass");
        client.setFirstName("Marta");
        client.setLastName("Martinez");
        client.setSecondLastName("Garcia");
        client.setDNI("12345678A");
        client.setApiToken("token");

        BankAccount account = new BankAccount();
        account.setId(1L);
        account.setBalance(new BigDecimal("100.00"));
        account.setIban("ES1234567890");
        account.setClient(client);

        client.setBankAccounts(List.of(account));

        ClientJpaEntity entity = mapper.toEntity(client);

        assertNotNull(entity);
        assertEquals(clientId, entity.getId());
        assertEquals(client.getLogin(), entity.getLogin());
        assertEquals(client.getDNI(), entity.getDNI());
        assertEquals(client.getApiToken(), entity.getApiToken());

        assertNotNull(entity.getBankAccounts());
        assertEquals(1, entity.getBankAccounts().size());
        BankAccountJpaEntity accountEntity = entity.getBankAccounts().get(0);
        assertEquals(account.getIban(), accountEntity.getIban());
        assertNotNull(accountEntity.getClient());
        assertEquals(clientId, accountEntity.getClient().getId());
    }

    @Test
    void toEntity_ShouldReturnNull_WhenModelIsNull() {
        assertNull(mapper.toEntity(null));
    }

    @Test
    void toModel_ShouldMapFields_WhenEntityIsNotNull() {
        UUID clientId = UUID.randomUUID();
        ClientJpaEntity entity = new ClientJpaEntity();
        entity.setId(clientId);
        entity.setLogin("ana");
        entity.setPassword("pass");
        entity.setFirstName("Ana");
        entity.setLastName("Perez");
        entity.setSecondLastName("Diaz");
        entity.setDNI("87654321B");
        entity.setApiToken("token2");

        BankAccountJpaEntity account = new BankAccountJpaEntity();
        account.setId(2L);
        account.setBalance(new BigDecimal("200.00"));
        account.setIban("ES0987654321");
        entity.setBankAccounts(List.of(account));

        Client model = mapper.toModel(entity);

        assertNotNull(model);
        assertEquals(clientId, model.getId());
        assertEquals(entity.getLogin(), model.getLogin());
        assertEquals(entity.getDNI(), model.getDNI());
        assertEquals(entity.getApiToken(), model.getApiToken());

        assertNotNull(model.getBankAccounts());
        assertEquals(1, model.getBankAccounts().size());
        assertNull(model.getBankAccounts().get(0).getClient());
    }

    @Test
    void toModel_ShouldReturnNull_WhenEntityIsNull() {
        assertNull(mapper.toModel(null));
    }

    @Test
    void toEntityWithoutBankAccounts_ShouldMapFields_WhenModelIsNotNull() {
        UUID clientId = UUID.randomUUID();
        Client client = new Client();
        client.setId(clientId);
        client.setLogin("luis");
        client.setDNI("11223344C");

        ClientJpaEntity entity = mapper.toEntityWithoutBankAccounts(client);

        assertNotNull(entity);
        assertEquals(clientId, entity.getId());
        assertEquals(client.getLogin(), entity.getLogin());
        assertEquals(client.getDNI(), entity.getDNI());
        assertNotNull(entity.getBankAccounts());
        assertTrue(entity.getBankAccounts().isEmpty());
    }

    @Test
    void toModelWithoutBankAccounts_ShouldMapFields_WhenEntityIsNotNull() {
        UUID clientId = UUID.randomUUID();
        ClientJpaEntity entity = new ClientJpaEntity();
        entity.setId(clientId);
        entity.setLogin("luis");
        entity.setDNI("11223344C");

        Client model = mapper.toModelWithoutBankAccounts(entity);

        assertNotNull(model);
        assertEquals(clientId, model.getId());
        assertEquals(entity.getLogin(), model.getLogin());
        assertEquals(entity.getDNI(), model.getDNI());
        assertNotNull(model.getBankAccounts());
        assertTrue(model.getBankAccounts().isEmpty());
    }
}
