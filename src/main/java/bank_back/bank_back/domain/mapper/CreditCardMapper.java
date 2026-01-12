package bank_back.bank_back.domain.mapper;

import org.springframework.stereotype.Component;

import bank_back.bank_back.domain.dto.CreditCardDto;
import bank_back.bank_back.domain.model.CreditCard;

@Component
public class CreditCardMapper {

    public CreditCardDto toDto(CreditCard model) {
        if (model == null) {
            return null;
        }
        return new CreditCardDto(
<<<<<<< HEAD:src/main/java/banck_back/banck_back/domain/mapper/CreditCardMapper.java
                model.getId(),
                model.getNumber(),
                model.getExpirationDate(),
                model.getCvv(),
                model.getName());
=======
                null,
                model.getCardNumber(),
                model.getExpiryDate() != null
                        ? java.time.LocalDate.ofInstant(model.getExpiryDate().toInstant(),
                                java.time.ZoneId.systemDefault())
                        : null,
                model.getCvc(),
                model.getBankAccount() != null ? null : null);
>>>>>>> origin/develop:src/main/java/bank_back/bank_back/domain/mapper/CreditCardMapper.java
    }

    public CreditCard toModel(CreditCardDto dto) {
        if (dto == null) {
            return null;
        }
        CreditCard model = new CreditCard();
<<<<<<< HEAD:src/main/java/banck_back/banck_back/domain/mapper/CreditCardMapper.java
        model.setId(dto.id());
        model.setNumber(dto.number());
        model.setExpirationDate(dto.expirationDate());
        model.setCvv(dto.cvv());
        model.setName(dto.name());
=======

        model.setCardNumber(dto.number());
        model.setExpiryDate(dto.expirationDate() != null
                ? java.util.Date.from(dto.expirationDate().atStartOfDay(java.time.ZoneId.systemDefault()).toInstant())
                : null);
        model.setCvc(dto.cvv());

>>>>>>> origin/develop:src/main/java/bank_back/bank_back/domain/mapper/CreditCardMapper.java
        return model;
    }
}
