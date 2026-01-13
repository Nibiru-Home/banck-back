package bank_back.bank_back.persistence.dao.jpa.impl;

import bank_back.bank_back.persistence.dao.jpa.ClientJpaDao;
import bank_back.bank_back.persistence.dao.jpa.entity.ClientJpaEntity;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClientJpaDaoImpl extends GenericJpaDaoImpl<ClientJpaEntity, Long> implements ClientJpaDao {

    public ClientJpaDaoImpl() {
        super(ClientJpaEntity.class);
    }

    @Override
    public Optional<ClientJpaEntity> findByLogin(String login) {
        TypedQuery<ClientJpaEntity> query = entityManager.createQuery(
                "SELECT c FROM ClientJpaEntity c WHERE c.login = :login", ClientJpaEntity.class);
        query.setParameter("login", login);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ClientJpaEntity> findByDNI(String DNI) {
        TypedQuery<ClientJpaEntity> query = entityManager.createQuery(
                "SELECT c FROM ClientJpaEntity c WHERE c.DNI = :DNI", ClientJpaEntity.class);
        query.setParameter("DNI", DNI);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
