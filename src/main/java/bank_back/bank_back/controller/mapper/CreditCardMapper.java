package bank_back.bank_back.controller.mapper;

import bank_back.bank_back.domain.dto.CreditCardDto;
import bank_back.bank_back.controller.webmodel.request.CreditCardRequest;
import bank_back.bank_back.controller.webmodel.response.CreditCardResponse;

public class CreditCardMapper {
    private static CreditCardMapper INSTANCE;

    private CreditCardMapper() {
    }

    public static CreditCardMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CreditCardMapper();
        }
        return INSTANCE;
    }

    public CreditCardDto creditCardRequestToCreditCardDto(CreditCardRequest creditCardRequest) {
        if (creditCardRequest == null) {
            return null;
        }

        return new CreditCardDto(
                creditCardRequest.id(),
                creditCardRequest.number(),
                creditCardRequest.expirationDate(),
                creditCardRequest.cvv(),
                creditCardRequest.name());
    }

    public CreditCardResponse creditCardDtoToCreditCardResponse(CreditCardDto creditCardDto) {
        if (creditCardDto == null) {
            return null;
        }

        return new CreditCardResponse(
                creditCardDto.id(),
                creditCardDto.number(),
                creditCardDto.expirationDate(),
                creditCardDto.cvv(),
                creditCardDto.name());
    }
}
