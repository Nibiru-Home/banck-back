package bank_back.bank_back.domain.service;

import java.util.List;
import java.util.UUID;

import bank_back.bank_back.domain.model.Client;

public interface ClientService {
    List<Client> findAll();

    Client findById(UUID id);

    Client create(Client client);

    Client update(UUID id, Client client);

    void delete(UUID id);
}
