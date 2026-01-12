package bank_back.bank_back.domain.service.impl;

import bank_back.bank_back.domain.model.Client;
import bank_back.bank_back.domain.repository.ClientRepository;
import bank_back.bank_back.domain.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public Client create(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client update(Long id, Client client) {
        if (clientRepository.findById(id).isEmpty()) {
            return null;
        }
        client.setId(id);
        return clientRepository.save(client);
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
