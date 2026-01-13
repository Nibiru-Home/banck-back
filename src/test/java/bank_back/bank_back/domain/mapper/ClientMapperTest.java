package bank_back.bank_back.domain.mapper;

import bank_back.bank_back.domain.dto.BankAccountDto;
import bank_back.bank_back.domain.dto.ClientDto;
import bank_back.bank_back.domain.model.BankAccount;
import bank_back.bank_back.domain.model.Client;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ClientMapperTest {

    private final ClientMapper mapper = ClientMapper.getInstance();

    @Test
    void toDto_ShouldMapAllFields_WhenModelIsNotNull() {
        BankAccount account = new BankAccount();
        account.setIban("ES1234567890");
        account.setBalance(new BigDecimal("100.00"));
        account.setClient(null);

        Client model = new Client();
        model.setId(UUID.randomUUID());
        model.setLogin("testuser");
        model.setPassword("password");
        model.setFirstName("John");
        model.setLastName("Doe");
        model.setSecondLastName("Smith");
        model.setDNI("12345678A");
        model.setApiToken("token123");

        List<BankAccount> accounts = new ArrayList<>();
        accounts.add(account);
        model.setBankAccounts(accounts);

        ClientDto dto = mapper.toDto(model);

        assertNotNull(dto);
        assertEquals(model.getId(), dto.id());
        assertEquals(model.getLogin(), dto.login());
        assertEquals(model.getPassword(), dto.password());
        assertEquals(model.getFirstName(), dto.firstName());
        assertEquals(model.getLastName(), dto.lastName());
        assertEquals(model.getSecondLastName(), dto.secondLastName());
        assertEquals(model.getDNI(), dto.DNI());
        assertEquals(model.getApiToken(), dto.apiToken());

        assertNotNull(dto.bankAccounts());
        assertEquals(1, dto.bankAccounts().size());
        assertEquals(account.getIban(), dto.bankAccounts().get(0).iban());
        assertNull(dto.bankAccounts().get(0).client());
    }

    @Test
    void toDto_ShouldReturnNull_WhenModelIsNull() {
        assertNull(mapper.toDto(null));
    }

    @Test
    void toModel_ShouldMapAllFields_WhenDtoIsNotNull() {
        BankAccountDto accountDto = new BankAccountDto(
                10L,
                new BigDecimal("100.00"),
                "ES1234567890",
                null,
                null,
                null);

        ClientDto dto = new ClientDto(
                UUID.randomUUID(),
                "testuser",
                "password",
                "John",
                "Doe",
                "Smith",
                "12345678A",
                "token123",
                List.of(accountDto));

        Client model = mapper.toModel(dto);

        assertNotNull(model);
        assertEquals(dto.id(), model.getId());
        assertEquals(dto.login(), model.getLogin());

        // Password and ApiToken are NOT mapped in toModel by design (usually)
        // Wait, check mapper implementation. ClientMapper uses:
        // model.setLogin(dto.login());
        // model.setFirstName(dto.firstName());
        // ...
        // It does NOT set password or apiToken in toModel. So assertions below are
        // correct.

        // Wait, looking at ClientMapper.java (viewed in step 95):
        /*
         * public Client toModel(ClientDto dto) {
         * ...
         * model.setId(dto.id());
         * model.setLogin(dto.login());
         * model.setFirstName(dto.firstName());
         * model.setLastName(dto.lastName());
         * model.setSecondLastName(dto.secondLastName());
         * model.setDNI(dto.DNI());
         * ...
         * }
         */
        // Indeed, password and apiToken are NOT set.

        assertNull(model.getPassword());
        assertNull(model.getApiToken());

        assertEquals(dto.firstName(), model.getFirstName());
        assertEquals(dto.lastName(), model.getLastName());
        assertEquals(dto.secondLastName(), model.getSecondLastName());
        assertEquals(dto.DNI(), model.getDNI());

        assertNotNull(model.getBankAccounts());
        assertEquals(1, model.getBankAccounts().size());
        assertEquals(accountDto.iban(), model.getBankAccounts().get(0).getIban());
    }

    @Test
    void toModel_ShouldReturnNull_WhenDtoIsNull() {
        assertNull(mapper.toModel(null));
    }
}
