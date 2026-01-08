package banck_back.banck_back.domain.mapper;

import banck_back.banck_back.domain.dto.CreditCardDto;
import banck_back.banck_back.domain.model.CreditCard;
import org.springframework.stereotype.Component;

@Component
public class CreditCardMapper {

    public CreditCardDto toDto(CreditCard model) {
        if (model == null) {
            return null;
        }
        return new CreditCardDto(
                null, // Model has no ID
                model.getCardNumber(),
                model.getExpiryDate() != null
                        ? java.time.LocalDate.ofInstant(model.getExpiryDate().toInstant(),
                                java.time.ZoneId.systemDefault())
                        : null,
                model.getCvc(),
                model.getBankAccount() != null ? null : null // Model BankAccount has no ID
        );
    }

    public CreditCard toModel(CreditCardDto dto) {
        if (dto == null) {
            return null;
        }
        CreditCard model = new CreditCard();
        // model.setId(dto.id()); // Model has no ID
        model.setCardNumber(dto.number());
        model.setExpiryDate(dto.expirationDate() != null
                ? java.util.Date.from(dto.expirationDate().atStartOfDay(java.time.ZoneId.systemDefault()).toInstant())
                : null);
        model.setCvc(dto.cvv());
        // BankAccount relationship handled elsewhere
        return model;
    }
}
