package bank_back.bank_back.controller.mapper;

import bank_back.bank_back.domain.dto.BankAccountDto;
import bank_back.bank_back.controller.webmodel.request.BankAccountRequest;
import bank_back.bank_back.controller.webmodel.response.BankAccountResponse;

public class BankAccountMapper {
    private static BankAccountMapper INSTANCE;

    private BankAccountMapper() {
    }

    public static BankAccountMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BankAccountMapper();
        }
        return INSTANCE;
    }

    public BankAccountDto bankAccountRequestToBankAccountDto(BankAccountRequest bankAccountRequest) {
        if (bankAccountRequest == null) {
            return null;
        }

        return new BankAccountDto(
                bankAccountRequest.id(),
                bankAccountRequest.balance(),
                bankAccountRequest.iban(),
                null,
                null,
                null);
    }

    public BankAccountResponse bankAccountDtoToBankAccountResponse(BankAccountDto bankAccountDto) {
        if (bankAccountDto == null) {
            return null;
        }

        return new BankAccountResponse(
                bankAccountDto.id(),
                bankAccountDto.balance(),
                bankAccountDto.iban(),
                bankAccountDto.client() != null
                        ? ClientMapper.getInstance().clientDtoToClientResponse(bankAccountDto.client())
                        : null,
                bankAccountDto.movements() != null
                        ? bankAccountDto.movements().stream()
                                .map(BankMovementMapper.getInstance()::bankMovementDtoToBankMovementResponse)
                                .toList()
                        : null,
                bankAccountDto.creditCards() != null
                        ? bankAccountDto.creditCards().stream()
                                .map(CreditCardMapper.getInstance()::creditCardDtoToCreditCardResponse)
                                .toList()
                        : null);
    }
}
