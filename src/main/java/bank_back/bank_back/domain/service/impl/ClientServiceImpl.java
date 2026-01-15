package bank_back.bank_back.domain.service.impl;

import bank_back.bank_back.domain.model.Client;
import bank_back.bank_back.domain.repository.ClientRepository;
import bank_back.bank_back.domain.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
    public Client findById(UUID id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public Client create(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client update(UUID id, Client client) {
        if (clientRepository.findById(id).isEmpty()) {
            return null;
        }
        client.setId(id);
        return clientRepository.save(client);
    }

    @Override
    public void delete(UUID id) {
        clientRepository.deleteById(id);
    }

    @Override
    public bank_back.bank_back.domain.dto.ClientDto login(String login, String password) {
        bank_back.bank_back.domain.dto.ClientDto client = clientRepository.findByLogin(login);
        if (client == null) {
            throw new bank_back.bank_back.domain.exception.BusinessException(
                    "Verifica tu numero de documento o contraseña");
        }
        if (!client.password().equals(password)) {
            throw new bank_back.bank_back.domain.exception.BusinessException(
                    "Verifica tu numero de documento o contraseña");
        }
        return client;
    }
}
