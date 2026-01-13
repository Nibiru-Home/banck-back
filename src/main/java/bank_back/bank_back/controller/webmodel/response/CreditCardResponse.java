package bank_back.bank_back.controller.webmodel.response;

import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;

public record CreditCardResponse(
                @NotNull Long id,
                @NotNull String number,
                @NotNull LocalDate expirationDate,
                @NotNull int cvv,
                @NotNull String name) {
}
