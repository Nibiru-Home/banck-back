package bank_back.bank_back.controller.webmodel.request;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record BankAccountRequest(
        @NotNull BigDecimal balance,
        @NotNull @Pattern(regexp = "^ES[0-9]{22}$", message = "Invalid IBAN format") String iban,
        Long clientId) {
}
