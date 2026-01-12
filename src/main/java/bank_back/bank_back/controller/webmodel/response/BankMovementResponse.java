package bank_back.bank_back.controller.webmodel.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import bank_back.bank_back.domain.model.MovementOrigin;
import bank_back.bank_back.domain.model.MovementType;

public record BankMovementResponse(
        Long id,
        BigDecimal amount,
        MovementType movementType,
        MovementOrigin movementOrigin,
        String concept,
        LocalDateTime timestamp,
        CreditCardResponse originCreditCard,
        BankAccountResponse destinationBankAccount) {
}
