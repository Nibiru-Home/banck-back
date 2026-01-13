package bank_back.bank_back.controller.mapper;

import bank_back.bank_back.controller.webmodel.request.BankAccountRequest;
import bank_back.bank_back.controller.webmodel.response.BankAccountResponse;
import bank_back.bank_back.domain.dto.BankAccountDto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountMapperTest {

    private final BankAccountMapper mapper = BankAccountMapper.getInstance();

    @Test
    void bankAccountRequestToBankAccountDto_ShouldReturnDto_WhenRequestIsValid() {
        BankAccountRequest request = new BankAccountRequest(
                1L,
                new BigDecimal("100.50"),
                "ES1234567890",
                10L);

        BankAccountDto dto = mapper.bankAccountRequestToBankAccountDto(request);

        assertNotNull(dto);
        assertEquals(request.id(), dto.id());
        assertEquals(request.balance(), dto.balance());
        assertEquals(request.iban(), dto.iban());
        assertNull(dto.client()); // Client ID is not mapped to the client object in this simplified mapping
        assertNull(dto.movements());
        assertNull(dto.creditCards());
    }

    @Test
    void bankAccountRequestToBankAccountDto_ShouldReturnNull_WhenRequestIsNull() {
        assertNull(mapper.bankAccountRequestToBankAccountDto(null));
    }

    @Test
    void bankAccountDtoToBankAccountResponse_ShouldReturnResponse_WhenDtoIsValid() {
        BankAccountDto dto = new BankAccountDto(
                1L,
                new BigDecimal("200.00"),
                "ES0987654321",
                null,
                null,
                null);

        BankAccountResponse response = mapper.bankAccountDtoToBankAccountResponse(dto);

        assertNotNull(response);
        assertEquals(dto.id(), response.id());
        assertEquals(dto.balance(), response.balance());
        assertEquals(dto.iban(), response.iban());
        assertNull(response.client());
        assertNull(response.movements());
        assertNull(response.creditCards());
    }

    @Test
    void bankAccountDtoToBankAccountResponse_ShouldReturnNull_WhenDtoIsNull() {
        assertNull(mapper.bankAccountDtoToBankAccountResponse(null));
    }
}
