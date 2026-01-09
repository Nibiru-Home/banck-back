package bank_back.bank_back.domain.dto;

import java.time.LocalDate;

public record CreditCardDto(
        Long id,
        String number,
        LocalDate expirationDate,
        String cvv,
        Long bankAccountId) {
}
