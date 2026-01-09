package bank_back.bank_back.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import bank_back.bank_back.domain.model.MovementOrigin;
import bank_back.bank_back.domain.model.MovementType;

public record BankMovementDto(
        Long id,
        BigDecimal amount,
        LocalDateTime timestamp,
        MovementType type,
        MovementOrigin origin,
        Long bankAccountId) {
}
