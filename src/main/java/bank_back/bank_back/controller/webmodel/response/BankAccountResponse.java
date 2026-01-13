package bank_back.bank_back.controller.webmodel.response;

import java.math.BigDecimal;
import java.util.List;
import jakarta.validation.constraints.NotNull;

public record BankAccountResponse(
                @NotNull Long id,
                @NotNull BigDecimal balance,
                @NotNull String iban,
                ClientResponse client,
                List<BankMovementResponse> movements,
                List<CreditCardResponse> creditCards) {
}
