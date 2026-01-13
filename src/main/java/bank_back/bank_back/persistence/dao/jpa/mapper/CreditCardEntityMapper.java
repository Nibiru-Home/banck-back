package bank_back.bank_back.persistence.dao.jpa.mapper;

import bank_back.bank_back.domain.model.CreditCard;
import bank_back.bank_back.persistence.dao.jpa.entity.CreditCardJpaEntity;

public class CreditCardEntityMapper {

    private static CreditCardEntityMapper INSTANCE;

    private CreditCardEntityMapper() {
    }

    public static CreditCardEntityMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CreditCardEntityMapper();
        }
        return INSTANCE;
    }

    public CreditCardJpaEntity toEntity(CreditCard model) {
        if (model == null) {
            return null;
        }
        CreditCardJpaEntity entity = new CreditCardJpaEntity();
        entity.setId(model.getId());
        entity.setNumber(model.getNumber());
        entity.setExpirationDate(model.getExpirationDate());
        entity.setCvv(model.getCvv());
        entity.setName(model.getName());
        entity.setBankAccount(BankAccountEntityMapper.getInstance().toEntityWithoutClient(model.getBankAccount()));
        return entity;
    }

    public CreditCard toModel(CreditCardJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        CreditCard model = new CreditCard();
        model.setId(entity.getId());
        model.setNumber(entity.getNumber());
        model.setExpirationDate(entity.getExpirationDate());
        model.setCvv(entity.getCvv());
        model.setName(entity.getName());
        model.setBankAccount(BankAccountEntityMapper.getInstance().toModelWithoutClient(entity.getBankAccount()));
        return model;
    }

    public CreditCardJpaEntity toEntityWithoutBankAccount(CreditCard model) {
        if (model == null) {
            return null;
        }
        CreditCardJpaEntity entity = new CreditCardJpaEntity();
        entity.setId(model.getId());
        entity.setNumber(model.getNumber());
        entity.setExpirationDate(model.getExpirationDate());
        entity.setCvv(model.getCvv());
        entity.setName(model.getName());
        return entity;
    }

    public CreditCard toModelWithoutBankAccount(CreditCardJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        CreditCard model = new CreditCard();
        model.setId(entity.getId());
        model.setNumber(entity.getNumber());
        model.setExpirationDate(entity.getExpirationDate());
        model.setCvv(entity.getCvv());
        model.setName(entity.getName());
        return model;
    }
}
