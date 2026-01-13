package bank_back.bank_back.persistence.dao.jpa.mapper;

import bank_back.bank_back.domain.model.BankAccount;
import bank_back.bank_back.persistence.dao.jpa.entity.BankAccountJpaEntity;

import java.util.stream.Collectors;

public class BankAccountEntityMapper {

    private static BankAccountEntityMapper INSTANCE;

    private BankAccountEntityMapper() {
    }

    public static BankAccountEntityMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BankAccountEntityMapper();
        }
        return INSTANCE;
    }

    public BankAccountJpaEntity toEntity(BankAccount model) {
        if (model == null) {
            return null;
        }
        BankAccountJpaEntity entity = new BankAccountJpaEntity();
        entity.setId(model.getId());
        entity.setBalance(model.getBalance());
        entity.setIban(model.getIban());
        entity.setClient(ClientEntityMapper.getInstance().toEntityWithoutBankAccounts(model.getClient()));

        if (model.getMovements() != null) {
            entity.setMovements(model.getMovements().stream()
                    .map(BankMovementEntityMapper.getInstance()::toEntityWithoutBankAccount)
                    .peek(m -> m.setBankAccount(entity))
                    .collect(Collectors.toList()));
        }

        if (model.getCreditCards() != null) {
            entity.setCreditCards(model.getCreditCards().stream()
                    .map(CreditCardEntityMapper.getInstance()::toEntityWithoutBankAccount)
                    .peek(c -> c.setBankAccount(entity))
                    .collect(Collectors.toList()));
        }

        return entity;
    }

    public BankAccount toModel(BankAccountJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        BankAccount model = new BankAccount();
        model.setId(entity.getId());
        model.setBalance(entity.getBalance());
        model.setIban(entity.getIban());
        model.setClient(ClientEntityMapper.getInstance().toModelWithoutBankAccounts(entity.getClient()));

        if (entity.getMovements() != null) {
            model.setMovements(entity.getMovements().stream()
                    .map(BankMovementEntityMapper.getInstance()::toModelWithoutBankAccount)
                    .collect(Collectors.toList()));
        }

        if (entity.getCreditCards() != null) {
            model.setCreditCards(entity.getCreditCards().stream()
                    .map(CreditCardEntityMapper.getInstance()::toModelWithoutBankAccount)
                    .collect(Collectors.toList()));
        }

        return model;
    }

    public BankAccountJpaEntity toEntityWithoutClient(BankAccount model) {
        if (model == null) {
            return null;
        }
        BankAccountJpaEntity entity = new BankAccountJpaEntity();
        entity.setId(model.getId());
        entity.setBalance(model.getBalance());
        entity.setIban(model.getIban());
        return entity;
    }

    public BankAccount toModelWithoutClient(BankAccountJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        BankAccount model = new BankAccount();
        model.setId(entity.getId());
        model.setBalance(entity.getBalance());
        model.setIban(entity.getIban());
        return model;
    }
}
