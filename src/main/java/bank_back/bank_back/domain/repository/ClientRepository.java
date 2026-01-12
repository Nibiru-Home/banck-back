package bank_back.bank_back.domain.repository;

import java.util.List;
import java.util.Optional;

import bank_back.bank_back.domain.model.Client;

public interface ClientRepository {
    List<Client> findAll();
    Optional<Client> findById(Long id);
    Client save(Client client);
    void deleteById(Long id);
}
