package bank_back.bank_back.domain.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Max;

public record CreditCardDto(
        Long id,
        @NotNull @Pattern(regexp = "^[0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4}$", message = "Invalid card number format (XXXX XXXX XXXX XXXX)") String number,
        @NotNull @Future LocalDate expirationDate,
        @NotNull @Max(999) int cvv,
        @NotNull String name) {
}