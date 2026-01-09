package bank_back.bank_back.domain.dto;

import java.math.BigDecimal;
import java.util.List;

public record BankAccountDto(
        Long id,
        BigDecimal balance,
        String iban,
        Long clientId,
        List<BankMovementDto> movements,
        List<CreditCardDto> creditCards) {
}
