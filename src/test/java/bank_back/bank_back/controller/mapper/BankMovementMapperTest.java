package bank_back.bank_back.controller.mapper;

import bank_back.bank_back.controller.webmodel.request.BankMovementRequest;
import bank_back.bank_back.controller.webmodel.response.BankMovementResponse;
import bank_back.bank_back.domain.dto.BankMovementDto;
import bank_back.bank_back.domain.model.MovementOrigin;
import bank_back.bank_back.domain.model.MovementType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BankMovementMapperTest {

    private final BankMovementMapper mapper = BankMovementMapper.getInstance();

    @Test
    void bankMovementRequestToBankMovementDto_ShouldReturnDto_WhenRequestIsValid() {
        BankMovementRequest request = new BankMovementRequest(
                1L,
                new BigDecimal("50.00"),
                MovementType.Add,
                MovementOrigin.Transfer,
                "Payment",
                LocalDateTime.now(),
                null,
                null);

        BankMovementDto dto = mapper.bankMovementRequestToBankMovementDto(request);

        assertNotNull(dto);
        assertEquals(request.id(), dto.id());
        assertEquals(request.amount(), dto.amount());
        assertEquals(request.movementType(), dto.movementType());
        assertEquals(request.movementOrigin(), dto.movementOrigin());
        assertEquals(request.concept(), dto.concept());
        assertEquals(request.timestamp(), dto.timestamp());
        assertNull(dto.originCreditCard());
        assertNull(dto.destinationBankAccount());
    }

    @Test
    void bankMovementRequestToBankMovementDto_ShouldReturnNull_WhenRequestIsNull() {
        assertNull(mapper.bankMovementRequestToBankMovementDto(null));
    }

    @Test
    void bankMovementDtoToBankMovementResponse_ShouldReturnResponse_WhenDtoIsValid() {
        BankMovementDto dto = new BankMovementDto(
                1L,
                new BigDecimal("75.00"),
                MovementType.Remove,
                MovementOrigin.Bank_card,
                "Purchase",
                LocalDateTime.now(),
                null,
                null);

        BankMovementResponse response = mapper.bankMovementDtoToBankMovementResponse(dto);

        assertNotNull(response);
        assertEquals(dto.id(), response.id());
        assertEquals(dto.amount(), response.amount());
        assertEquals(dto.movementType(), response.movementType());
        assertEquals(dto.movementOrigin(), response.movementOrigin());
        assertEquals(dto.concept(), response.concept());
        assertEquals(dto.timestamp(), response.timestamp());
        assertNull(response.originCreditCard());
        assertNull(response.destinationBankAccount());
    }

    @Test
    void bankMovementDtoToBankMovementResponse_ShouldReturnNull_WhenDtoIsNull() {
        assertNull(mapper.bankMovementDtoToBankMovementResponse(null));
    }
}
