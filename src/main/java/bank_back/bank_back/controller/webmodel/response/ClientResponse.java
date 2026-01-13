package bank_back.bank_back.controller.webmodel.response;

import java.util.List;
import jakarta.validation.constraints.NotNull;

public record ClientResponse(
                @NotNull Long id,
                @NotNull String login,
                @NotNull String firstName,
                String lastName,
                String secondLastName,
                @NotNull String DNI,
                String apiToken,
                List<BankAccountResponse> bankAccounts) {
}
