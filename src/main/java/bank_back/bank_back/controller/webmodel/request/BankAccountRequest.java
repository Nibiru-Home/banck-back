package bank_back.bank_back.controller.webmodel.request;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;

public record BankAccountRequest(
                Long id,
                @NotNull BigDecimal balance,
                @NotNull String iban,
                Long clientId) {
}
