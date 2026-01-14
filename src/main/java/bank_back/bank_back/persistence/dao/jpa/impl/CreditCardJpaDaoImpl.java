package bank_back.bank_back.persistence.dao.jpa.impl;

import bank_back.bank_back.persistence.dao.jpa.CreditCardJpaDao;
import bank_back.bank_back.persistence.dao.jpa.entity.CreditCardJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CreditCardJpaDaoImpl implements CreditCardJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    public CreditCardJpaDaoImpl() {
    }

    @Override
    public List<CreditCardJpaEntity> findAll(int page, int size) {
        return entityManager.createQuery("SELECT c FROM CreditCardJpaEntity c", CreditCardJpaEntity.class)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public Optional<CreditCardJpaEntity> findById(Long id) {
        return Optional.ofNullable(entityManager.find(CreditCardJpaEntity.class, id));
    }

    @Override
    public CreditCardJpaEntity insert(CreditCardJpaEntity jpaEntity) {
        entityManager.persist(jpaEntity);
        return jpaEntity;
    }

    @Override
    public CreditCardJpaEntity update(CreditCardJpaEntity jpaEntity) {
        return entityManager.merge(jpaEntity);
    }

    @Override
    public void deleteById(Long id) {
        CreditCardJpaEntity entity = entityManager.find(CreditCardJpaEntity.class, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public Optional<CreditCardJpaEntity> findByNumber(String number) {
        TypedQuery<CreditCardJpaEntity> query = entityManager.createQuery(
                "SELECT c FROM CreditCardJpaEntity c WHERE c.number = :number", CreditCardJpaEntity.class);
        query.setParameter("number", number);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
