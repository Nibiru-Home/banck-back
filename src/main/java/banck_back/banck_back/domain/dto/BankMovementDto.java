package banck_back.banck_back.domain.dto;

import banck_back.banck_back.domain.model.MovementOrigin;
import banck_back.banck_back.domain.model.MovementType;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BankMovementDto(
        Long id,
        BigDecimal amount,
        LocalDateTime timestamp,
        MovementType type,
        MovementOrigin origin,
        Long bankAccountId) {
}
