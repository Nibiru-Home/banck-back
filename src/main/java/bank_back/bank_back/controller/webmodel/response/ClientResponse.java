package bank_back.bank_back.controller.webmodel.response;

import java.util.List;

public record ClientResponse(
        Long id,
        String login,
        String firstName,
        String lastName,
        String secondLastName,
        String DNI,
        String apiToken,
        List<BankAccountResponse> bankAccounts) {
}
