package bank_back.bank_back.persistence.dao.jpa.mapper;

import bank_back.bank_back.domain.model.BankMovement;
import bank_back.bank_back.persistence.dao.jpa.entity.BankMovementJpaEntity;

public class BankMovementEntityMapper {

    private static BankMovementEntityMapper INSTANCE;

    private BankMovementEntityMapper() {
    }

    public static BankMovementEntityMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BankMovementEntityMapper();
        }
        return INSTANCE;
    }

    public BankMovementJpaEntity toEntity(BankMovement model) {
        if (model == null) {
            return null;
        }
        BankMovementJpaEntity entity = new BankMovementJpaEntity();
        entity.setId(model.getId());
        entity.setMovementType(model.getMovementType());
        entity.setMovementOrigin(model.getMovementOrigin());
        entity.setOriginCreditCard(
                CreditCardEntityMapper.getInstance().toEntityWithoutBankAccount(model.getOriginCreditCard()));
        entity.setTimestamp(model.getTimestamp());
        entity.setAmount(model.getAmount());
        entity.setConcept(model.getConcept());
        entity.setBankAccount(BankAccountEntityMapper.getInstance().toEntityWithoutClient(model.getBankAccount()));
        return entity;
    }

    public BankMovement toModel(BankMovementJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        BankMovement model = new BankMovement();
        model.setId(entity.getId());
        model.setMovementType(entity.getMovementType());
        model.setMovementOrigin(entity.getMovementOrigin());
        model.setOriginCreditCard(
                CreditCardEntityMapper.getInstance().toModelWithoutBankAccount(entity.getOriginCreditCard()));
        model.setTimestamp(entity.getTimestamp());
        model.setAmount(entity.getAmount());
        model.setConcept(entity.getConcept());
        model.setBankAccount(BankAccountEntityMapper.getInstance().toModelWithoutClient(entity.getBankAccount()));
        return model;
    }

    public BankMovementJpaEntity toEntityWithoutBankAccount(BankMovement model) {
        if (model == null) {
            return null;
        }
        BankMovementJpaEntity entity = new BankMovementJpaEntity();
        entity.setId(model.getId());
        entity.setMovementType(model.getMovementType());
        entity.setMovementOrigin(model.getMovementOrigin());
        entity.setOriginCreditCard(
                CreditCardEntityMapper.getInstance().toEntityWithoutBankAccount(model.getOriginCreditCard()));
        entity.setTimestamp(model.getTimestamp());
        entity.setAmount(model.getAmount());
        entity.setConcept(model.getConcept());
        return entity;
    }

    public BankMovement toModelWithoutBankAccount(BankMovementJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        BankMovement model = new BankMovement();
        model.setId(entity.getId());
        model.setMovementType(entity.getMovementType());
        model.setMovementOrigin(entity.getMovementOrigin());
        model.setOriginCreditCard(
                CreditCardEntityMapper.getInstance().toModelWithoutBankAccount(entity.getOriginCreditCard()));
        model.setTimestamp(entity.getTimestamp());
        model.setAmount(entity.getAmount());
        model.setConcept(entity.getConcept());
        return model;
    }
}
