package bank_back.bank_back.persistence.dao.jpa.impl;

import bank_back.bank_back.persistence.dao.jpa.ClientJpaDao;
import bank_back.bank_back.persistence.dao.jpa.entity.ClientJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import java.util.UUID;

@Repository
public class ClientJpaDaoImpl implements ClientJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    public ClientJpaDaoImpl() {
    }

    @Override
    public List<ClientJpaEntity> findAll(int page, int size) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ClientJpaEntity> cq = cb.createQuery(ClientJpaEntity.class);
        Root<ClientJpaEntity> rootEntry = cq.from(ClientJpaEntity.class);
        CriteriaQuery<ClientJpaEntity> all = cq.select(rootEntry);

        return entityManager.createQuery(all)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public Optional<ClientJpaEntity> findById(UUID id) {
        return Optional.ofNullable(entityManager.find(ClientJpaEntity.class, id));
    }

    @Override
    public ClientJpaEntity insert(ClientJpaEntity jpaEntity) {
        entityManager.persist(jpaEntity);
        return jpaEntity;
    }

    @Override
    public ClientJpaEntity update(ClientJpaEntity jpaEntity) {
        return entityManager.merge(jpaEntity);
    }

    @Override
    public void deleteById(UUID id) {
        ClientJpaEntity entity = entityManager.find(ClientJpaEntity.class, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
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
