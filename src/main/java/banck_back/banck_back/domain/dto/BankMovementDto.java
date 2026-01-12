package banck_back.banck_back.domain.dto;

import banck_back.banck_back.domain.model.MovementOrigin;
import banck_back.banck_back.domain.model.MovementType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;


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
