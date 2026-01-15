package bank_back.bank_back.persistence.dao.jpa.impl;

import bank_back.bank_back.persistence.dao.jpa.BankAccountJpaDao;
import bank_back.bank_back.persistence.dao.jpa.entity.BankAccountJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class BankAccountJpaDaoImpl implements BankAccountJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    public BankAccountJpaDaoImpl() {
    }

    @Override
    public List<BankAccountJpaEntity> findAll(int page, int size) {
        return entityManager.createQuery("SELECT b FROM BankAccountJpaEntity b", BankAccountJpaEntity.class)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public Optional<BankAccountJpaEntity> findById(Long id) {
        return Optional.ofNullable(entityManager.find(BankAccountJpaEntity.class, id));
    }

    @Override
    public BankAccountJpaEntity insert(BankAccountJpaEntity jpaEntity) {
        entityManager.persist(jpaEntity);
        return jpaEntity;
    }

    @Override
    public BankAccountJpaEntity update(BankAccountJpaEntity jpaEntity) {
        return entityManager.merge(jpaEntity);
    }

    @Override
    public void deleteById(Long id) {
        BankAccountJpaEntity entity = entityManager.find(BankAccountJpaEntity.class, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public Optional<BankAccountJpaEntity> findByIban(String iban) {
        TypedQuery<BankAccountJpaEntity> query = entityManager.createQuery(
                "SELECT b FROM BankAccountJpaEntity b WHERE b.iban = :iban", BankAccountJpaEntity.class);
        query.setParameter("iban", iban);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<BankAccountJpaEntity> findByCreditCardId(Long id) {
        TypedQuery<BankAccountJpaEntity> query = entityManager.createQuery(
                "SELECT b FROM BankAccountJpaEntity b JOIN b.creditCards c WHERE c.id = :creditCardId",
                BankAccountJpaEntity.class);
        query.setParameter("creditCardId", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<BankAccountJpaEntity> findByClientId(UUID clientId) {
        TypedQuery<BankAccountJpaEntity> query = entityManager.createQuery(
                "SELECT b FROM BankAccountJpaEntity b WHERE b.client.id = :clientId",
                BankAccountJpaEntity.class);
        query.setParameter("clientId", clientId);
        return query.getResultList();
    }
}
