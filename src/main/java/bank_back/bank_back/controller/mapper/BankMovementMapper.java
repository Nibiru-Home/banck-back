package bank_back.bank_back.controller.mapper;

import bank_back.bank_back.domain.dto.BankMovementDto;
import bank_back.bank_back.controller.webmodel.request.BankMovementRequest;
import bank_back.bank_back.controller.webmodel.response.BankMovementResponse;

public class BankMovementMapper {
    private static BankMovementMapper INSTANCE;

    private BankMovementMapper() {
    }

    public static BankMovementMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BankMovementMapper();
        }
        return INSTANCE;
    }

    public BankMovementDto bankMovementRequestToBankMovementDto(BankMovementRequest bankMovementRequest) {
        if (bankMovementRequest == null) {
            return null;
        }

        return new BankMovementDto(
                bankMovementRequest.id(),
                bankMovementRequest.amount(),
                bankMovementRequest.movementType(),
                bankMovementRequest.movementOrigin(),
                bankMovementRequest.concept(),
                bankMovementRequest.timestamp(),
                bankMovementRequest.originCreditCard() != null
                        ? CreditCardMapper.getInstance()
                                .creditCardRequestToCreditCardDto(bankMovementRequest.originCreditCard())
                        : null,
                bankMovementRequest.destinationBankAccount() != null
                        ? BankAccountMapper.getInstance()
                                .bankAccountRequestToBankAccountDto(bankMovementRequest.destinationBankAccount())
                        : null);
    }

    public BankMovementResponse bankMovementDtoToBankMovementResponse(BankMovementDto bankMovementDto) {
        if (bankMovementDto == null) {
            return null;
        }

        return new BankMovementResponse(
                bankMovementDto.id(),
                bankMovementDto.amount(),
                bankMovementDto.movementType(),
                bankMovementDto.movementOrigin(),
                bankMovementDto.concept(),
                bankMovementDto.timestamp(),
                bankMovementDto.originCreditCard() != null
                        ? CreditCardMapper.getInstance()
                                .creditCardDtoToCreditCardResponse(bankMovementDto.originCreditCard())
                        : null,
                bankMovementDto.destinationBankAccount() != null
                        ? BankAccountMapper.getInstance()
                                .bankAccountDtoToBankAccountResponse(bankMovementDto.destinationBankAccount())
                        : null);
    }
}
