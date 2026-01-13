package bank_back.bank_back.domain.mapper;

import bank_back.bank_back.domain.dto.CreditCardDto;
import bank_back.bank_back.domain.model.CreditCard;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardMapperTest {

    private final CreditCardMapper mapper = CreditCardMapper.getInstance();

    @Test
    void toDto_ShouldMapAllFields_WhenModelIsNotNull() {
        CreditCard model = new CreditCard();
        model.setId(1L);
        model.setNumber("1234567890123456");
        model.setExpirationDate(LocalDate.of(2025, 12, 31));
        model.setCvv(123);
        model.setName("John Doe");

        CreditCardDto dto = mapper.toDto(model);

        assertNotNull(dto);
        assertEquals(model.getId(), dto.id());
        assertEquals(model.getNumber(), dto.number());
        assertEquals(model.getExpirationDate(), dto.expirationDate());
        assertEquals(model.getCvv(), dto.cvv());
        assertEquals(model.getName(), dto.name());
    }

    @Test
    void toDto_ShouldReturnNull_WhenModelIsNull() {
        assertNull(mapper.toDto(null));
    }

    @Test
    void toModel_ShouldMapAllFields_WhenDtoIsNotNull() {
        CreditCardDto dto = new CreditCardDto(
                1L,
                "1234567890123456",
                LocalDate.of(2025, 12, 31),
                123,
                "John Doe");

        CreditCard model = mapper.toModel(dto);

        assertNotNull(model);
        assertEquals(dto.id(), model.getId());
        assertEquals(dto.number(), model.getNumber());
        assertEquals(dto.expirationDate(), model.getExpirationDate());
        assertEquals(dto.cvv(), model.getCvv());
        assertEquals(dto.name(), model.getName());
    }

    @Test
    void toModel_ShouldReturnNull_WhenDtoIsNull() {
        assertNull(mapper.toModel(null));
    }
}
