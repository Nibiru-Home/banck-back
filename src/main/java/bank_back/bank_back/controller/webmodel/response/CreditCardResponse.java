package bank_back.bank_back.controller.webmodel.response;

import java.time.LocalDate;

public record CreditCardResponse(
        Long id,
        String number,
        LocalDate expirationDate,
        int cvv,
        String name) {
}
