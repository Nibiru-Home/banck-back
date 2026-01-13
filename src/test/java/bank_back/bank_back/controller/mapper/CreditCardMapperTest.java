package bank_back.bank_back.controller.mapper;

import bank_back.bank_back.controller.webmodel.request.CreditCardRequest;
import bank_back.bank_back.controller.webmodel.response.CreditCardResponse;
import bank_back.bank_back.domain.dto.CreditCardDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardMapperTest {

    private final CreditCardMapper mapper = CreditCardMapper.getInstance();

    @Test
    void creditCardRequestToCreditCardDto_ShouldReturnDto_WhenRequestIsValid() {
        CreditCardRequest request = new CreditCardRequest(
                1L,
                "1234567812345678",
                LocalDate.of(2028, 12, 31),
                123,
                "John Doe");

        CreditCardDto dto = mapper.creditCardRequestToCreditCardDto(request);

        assertNotNull(dto);
        assertEquals(request.id(), dto.id());
        assertEquals(request.number(), dto.number());
        assertEquals(request.expirationDate(), dto.expirationDate());
        assertEquals(request.cvv(), dto.cvv());
        assertEquals(request.name(), dto.name());
    }

    @Test
    void creditCardRequestToCreditCardDto_ShouldReturnNull_WhenRequestIsNull() {
        assertNull(mapper.creditCardRequestToCreditCardDto(null));
    }

    @Test
    void creditCardDtoToCreditCardResponse_ShouldReturnResponse_WhenDtoIsValid() {
        CreditCardDto dto = new CreditCardDto(
                1L,
                "8765432187654321",
                LocalDate.of(2029, 6, 30),
                456,
                "Jane Doe");

        CreditCardResponse response = mapper.creditCardDtoToCreditCardResponse(dto);

        assertNotNull(response);
        assertEquals(dto.id(), response.id());
        assertEquals(dto.number(), response.number());
        assertEquals(dto.expirationDate(), response.expirationDate());
        assertEquals(dto.cvv(), response.cvv());
        assertEquals(dto.name(), response.name());
    }

    @Test
    void creditCardDtoToCreditCardResponse_ShouldReturnNull_WhenDtoIsNull() {
        assertNull(mapper.creditCardDtoToCreditCardResponse(null));
    }
}
