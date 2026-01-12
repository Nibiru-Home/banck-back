package banck_back.banck_back.domain.dto;

import java.math.BigDecimal;
import java.util.List;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record BankAccountDto(
        Long id,
        @NotNull BigDecimal balance,
        @NotNull @Pattern(regexp = "^ES[0-9]{22}$", message = "Invalid IBAN format") String iban, ClientDto client,
        List<BankMovementDto> movements,
        List<CreditCardDto> creditCards) {
}
