package bank_back.bank_back.domain.dto;

import java.util.List;
import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClientDto(
                UUID id,
                @NotNull String login,
                @NotNull String password,
                @NotNull String firstName,
                String lastName,
                String secondLastName,
                @NotNull @Pattern(regexp = "^[0-9]{8}[A-Z]$", message = "Invalid DNI format") String DNI,
                String apiToken,
                List<BankAccountDto> bankAccounts) {
}
