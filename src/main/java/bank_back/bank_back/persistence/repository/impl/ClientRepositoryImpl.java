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

import java.util.UUID;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    private final ClientJpaDao clientJpaDao;
    private final bank_back.bank_back.persistence.dao.jpa.TokenJpaDao tokenJpaDao;

    public ClientRepositoryImpl(ClientJpaDao clientJpaDao,
            bank_back.bank_back.persistence.dao.jpa.TokenJpaDao tokenJpaDao) {
        this.clientJpaDao = clientJpaDao;
        this.tokenJpaDao = tokenJpaDao;
    }

    @Override
    public List<Client> findAll() {
        return clientJpaDao.findAll(0, Integer.MAX_VALUE).stream()
                .map(ClientEntityMapper.getInstance()::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Client> findById(UUID id) {
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
    public void deleteById(UUID id) {
        clientJpaDao.deleteById(id);
    }

    @Override
    public bank_back.bank_back.domain.dto.ClientDto findByLogin(String login) {
         java.util.Optional<bank_back.bank_back.persistence.dao.jpa.entity.ClientJpaEntity> entityOpt = clientJpaDao
                .findByLogin(login);
        if (entityOpt.isEmpty()) {
            entityOpt = clientJpaDao.findByDNI(login);
        }

        return entityOpt
                .map(clientEntity -> {
                    String token = tokenJpaDao.findByClientId(clientEntity.getId())
                            .map(bank_back.bank_back.persistence.dao.jpa.entity.TokenJpaEntity::getValue)
                            .orElse(null);

                    bank_back.bank_back.domain.model.Client client = ClientEntityMapper.getInstance()
                            .toModel(clientEntity);

                    return new bank_back.bank_back.domain.dto.ClientDto(
                            client.getId(),
                            client.getLogin(),
                            client.getPassword(),
                            client.getFirstName(),
                            client.getLastName(),
                            client.getSecondLastName(),
                            client.getDNI(),
                            token,
                            null);
                })
                .orElse(null);
    }
}