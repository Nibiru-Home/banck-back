package bank_back.bank_back.persistence.repository.mapper;

import bank_back.bank_back.domain.model.Client;
import bank_back.bank_back.persistence.dao.jpa.entity.ClientJpaEntity;

import java.util.stream.Collectors;

public class ClientEntityMapper {

    private static ClientEntityMapper INSTANCE;

    private ClientEntityMapper() {
    }

    public static ClientEntityMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClientEntityMapper();
        }
        return INSTANCE;
    }

    public ClientJpaEntity toEntity(Client model) {
        if (model == null) {
            return null;
        }
        ClientJpaEntity entity = new ClientJpaEntity();
        entity.setId(model.getId());
        entity.setLogin(model.getLogin());
        entity.setPassword(model.getPassword());
        entity.setFirstName(model.getFirstName());
        entity.setLastName(model.getLastName());
        entity.setSecondLastName(model.getSecondLastName());
        entity.setDNI(model.getDNI());
        entity.setApiToken(model.getApiToken());

        if (model.getBankAccounts() != null) {
            entity.setBankAccounts(model.getBankAccounts().stream()
                    .map(BankAccountEntityMapper.getInstance()::toEntityWithoutClient)
                    .peek(ba -> ba.setClient(entity))
                    .collect(Collectors.toList()));
        }

        return entity;
    }

    public Client toModel(ClientJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        Client model = new Client();
        model.setId(entity.getId());
        model.setLogin(entity.getLogin());
        model.setPassword(entity.getPassword());
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());
        model.setSecondLastName(entity.getSecondLastName());
        model.setDNI(entity.getDNI());
        model.setApiToken(entity.getApiToken());

        if (entity.getBankAccounts() != null) {
            model.setBankAccounts(entity.getBankAccounts().stream()
                    .map(BankAccountEntityMapper.getInstance()::toModelWithoutClient)
                    .collect(Collectors.toList()));
        }

        return model;
    }

    public ClientJpaEntity toEntityWithoutBankAccounts(Client model) {
        if (model == null) {
            return null;
        }
        ClientJpaEntity entity = new ClientJpaEntity();
        entity.setId(model.getId());
        entity.setLogin(model.getLogin());
        entity.setPassword(model.getPassword());
        entity.setFirstName(model.getFirstName());
        entity.setLastName(model.getLastName());
        entity.setSecondLastName(model.getSecondLastName());
        entity.setDNI(model.getDNI());
        entity.setApiToken(model.getApiToken());
        return entity;
    }

    public Client toModelWithoutBankAccounts(ClientJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        Client model = new Client();
        model.setId(entity.getId());
        model.setLogin(entity.getLogin());
        model.setPassword(entity.getPassword());
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());
        model.setSecondLastName(entity.getSecondLastName());
        model.setDNI(entity.getDNI());
        model.setApiToken(entity.getApiToken());
        return model;
    }
}
