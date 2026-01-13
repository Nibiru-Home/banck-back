package bank_back.bank_back.persistence.repository.impl;

import bank_back.bank_back.domain.model.Client;
import bank_back.bank_back.domain.repository.ClientRepository;
import bank_back.bank_back.persistence.dao.jpa.ClientJpaDao;
import bank_back.bank_back.persistence.repository.mapper.ClientEntityMapper;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    private final ClientJpaDao clientJpaDao;

    public ClientRepositoryImpl(ClientJpaDao clientJpaDao) {
        this.clientJpaDao = clientJpaDao;
    }

    @Override
    public List<Client> findAll() {
        return clientJpaDao.findAll(0, Integer.MAX_VALUE).stream()
                .map(ClientEntityMapper.getInstance()::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Client> findById(Long id) {
        return clientJpaDao.findById(id)
                .map(ClientEntityMapper.getInstance()::toModel);
    }

    @Override
    @Transactional
    public Client save(Client client) {
        if (client.getId() == null) {
            return ClientEntityMapper.getInstance().toModel(
                    clientJpaDao.insert(ClientEntityMapper.getInstance().toEntity(client)));
        } else {
            return ClientEntityMapper.getInstance().toModel(
                    clientJpaDao.update(ClientEntityMapper.getInstance().toEntity(client)));
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        clientJpaDao.deleteById(id);
    }
}
