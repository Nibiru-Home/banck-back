package bank_back.bank_back.domain.service;

import java.util.List;

import bank_back.bank_back.domain.model.Client;

public interface ClientService {
    List<Client> findAll();
    Client findById(Long id);
    Client create(Client client);
    Client update(Long id, Client client);
    void delete(Long id);
}
