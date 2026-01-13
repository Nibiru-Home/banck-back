package bank_back.bank_back.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import bank_back.bank_back.domain.model.Client;

public interface ClientRepository {
    List<Client> findAll();

    Optional<Client> findById(UUID id);

    Client save(Client client);

    void deleteById(UUID id);
}
