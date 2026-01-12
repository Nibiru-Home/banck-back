package bank_back.bank_back.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;


import bank_back.bank_back.domain.model.MovementOrigin;
import bank_back.bank_back.domain.model.MovementType;

public record BankMovementDto(
        Long id,
        @NotNull BigDecimal amount,
        @NotNull MovementType movementType,
        @NotNull MovementOrigin movementOrigin,
        String concept,
        @NotNull LocalDateTime timestamp,
        CreditCardDto originCreditCard,
        BankAccountDto destinationBankAccount) {
}
