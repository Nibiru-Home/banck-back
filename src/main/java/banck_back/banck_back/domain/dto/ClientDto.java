package banck_back.banck_back.domain.dto;

import java.util.List;

public record ClientDto(
        Long id,
        String login,
        String firstName,
        String lastName,
        String secondLastName,
        String nationalId,
        List<BankAccountDto> bankAccounts) {
}
