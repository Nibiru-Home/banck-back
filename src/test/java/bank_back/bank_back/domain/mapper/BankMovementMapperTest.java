package bank_back.bank_back.domain.mapper;

import bank_back.bank_back.domain.dto.BankMovementDto;
import bank_back.bank_back.domain.model.BankMovement;
import bank_back.bank_back.domain.model.MovementOrigin;
import bank_back.bank_back.domain.model.MovementType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BankMovementMapperTest {

    private final BankMovementMapper mapper = new BankMovementMapper();

    @Test
    void toDto_ShouldMapAllFields_WhenModelIsNotNull() {
        BankMovement model = new BankMovement();
        model.setId(1L);
        model.setAmount(new BigDecimal("100.50"));
        model.setMovementType(MovementType.Remove);
        model.setMovementOrigin(MovementOrigin.Bank_card);
        model.setConcept("Withdrawal");
        model.setTimestamp(LocalDateTime.of(2023, 1, 1, 12, 0));

        BankMovementDto dto = mapper.toDto(model);

        assertNotNull(dto);
        assertEquals(model.getId(), dto.id());
        assertEquals(model.getAmount(), dto.amount());
        assertEquals(model.getMovementType(), dto.movementType());
        assertEquals(model.getMovementOrigin(), dto.movementOrigin());
        assertEquals(model.getConcept(), dto.concept());
        assertEquals(model.getTimestamp(), dto.timestamp());
    }

    @Test
    void toDto_ShouldReturnNull_WhenModelIsNull() {
        assertNull(mapper.toDto(null));
    }

    @Test
    void toModel_ShouldMapAllFields_WhenDtoIsNotNull() {
        BankMovementDto dto = new BankMovementDto(
                1L,
                new BigDecimal("100.50"),
                MovementType.Remove,
                MovementOrigin.Bank_card,
                "Withdrawal",
                LocalDateTime.of(2023, 1, 1, 12, 0),
                null,
                null);

        BankMovement model = mapper.toModel(dto);

        assertNotNull(model);
        assertNull(model.getId());
        assertEquals(dto.amount(), model.getAmount());
        assertEquals(dto.movementType(), model.getMovementType());
        assertEquals(dto.movementOrigin(), model.getMovementOrigin());
        assertEquals(dto.concept(), model.getConcept());
        assertEquals(dto.timestamp(), model.getTimestamp());
    }

    @Test
    void toModel_ShouldReturnNull_WhenDtoIsNull() {
        assertNull(mapper.toModel(null));
    }
}
