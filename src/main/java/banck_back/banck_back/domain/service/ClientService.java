package banck_back.banck_back.domain.service;

import banck_back.banck_back.domain.model.Client;
import java.util.List;

public interface ClientService {
    List<Client> findAll();
    Client findById(Long id);
    Client create(Client client);
    Client update(Long id, Client client);
    void delete(Long id);
}
