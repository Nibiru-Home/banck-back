package bank_back.bank_back.domain.dto;

import java.math.BigDecimal;
import java.util.List;
import jakarta.validation.constraints.NotNull;

public record BankAccountDto(
                Long id,
                @NotNull BigDecimal balance,
                @NotNull String iban, ClientDto client,
                List<BankMovementDto> movements,
                List<CreditCardDto> creditCards) {
}
