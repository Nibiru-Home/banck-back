package bank_back.bank_back.domain.mapper;

import bank_back.bank_back.domain.dto.CreditCardDto;
import bank_back.bank_back.domain.model.CreditCard;

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
