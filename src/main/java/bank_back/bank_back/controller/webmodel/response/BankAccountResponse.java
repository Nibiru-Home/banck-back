package bank_back.bank_back.controller.webmodel.response;

import java.math.BigDecimal;
import java.util.List;

public record BankAccountResponse(
        Long id,
        BigDecimal balance,
        String iban,
        ClientResponse client,
        List<BankMovementResponse> movements,
        List<CreditCardResponse> creditCards) {
}
