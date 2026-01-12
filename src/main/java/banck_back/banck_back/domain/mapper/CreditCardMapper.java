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
                model.getId(),
                model.getNumber(),
                model.getExpirationDate(),
                model.getCvv(),
                model.getName());
    }

    public CreditCard toModel(CreditCardDto dto) {
        if (dto == null) {
            return null;
        }
        CreditCard model = new CreditCard();
        model.setId(dto.id());
        model.setNumber(dto.number());
        model.setExpirationDate(dto.expirationDate());
        model.setCvv(dto.cvv());
        model.setName(dto.name());
        return model;
    }
}
