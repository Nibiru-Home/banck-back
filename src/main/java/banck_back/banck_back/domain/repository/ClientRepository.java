package banck_back.banck_back.domain.repository;

import banck_back.banck_back.domain.model.Client;
import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    List<Client> findAll();
    Optional<Client> findById(Long id);
    Client save(Client client);
    void deleteById(Long id);
}
