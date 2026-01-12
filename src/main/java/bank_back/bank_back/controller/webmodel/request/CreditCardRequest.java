package bank_back.bank_back.controller.webmodel.request;

import java.time.LocalDate;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Max;

public record CreditCardRequest(
        @NotNull @Pattern(regexp = "^[0-9]{16}$", message = "Invalid card number") String number,
        @NotNull @Future LocalDate expirationDate,
        @NotNull @Max(999) int cvv,
        @NotNull String name) {
}
