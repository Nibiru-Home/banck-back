package bank_back.bank_back.controller.webmodel.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;

import bank_back.bank_back.domain.model.MovementOrigin;
import bank_back.bank_back.domain.model.MovementType;

public record BankMovementRequest(
                Long id,
                @NotNull BigDecimal amount,
                @NotNull MovementType movementType,
                @NotNull MovementOrigin movementOrigin,
                String concept,
                @NotNull LocalDateTime timestamp,
                CreditCardRequest originCreditCard,
                BankAccountRequest destinationBankAccount) {
}
