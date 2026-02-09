package bank_back.bank_back.persistence.dao.jpa.impl;

import bank_back.bank_back.persistence.dao.jpa.BankMovementJpaDao;
import bank_back.bank_back.persistence.dao.jpa.entity.BankMovementJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BankMovementJpaDaoImpl implements BankMovementJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    public BankMovementJpaDaoImpl() {
    }

    @Override
    public List<BankMovementJpaEntity> findAll(int page, int size) {
        return entityManager.createQuery("SELECT b FROM BankMovementJpaEntity b", BankMovementJpaEntity.class)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public Optional<BankMovementJpaEntity> findById(Long id) {
        return Optional.ofNullable(entityManager.find(BankMovementJpaEntity.class, id));
    }

    @Override
    public BankMovementJpaEntity insert(BankMovementJpaEntity jpaEntity) {
        entityManager.persist(jpaEntity);
        return jpaEntity;
    }

    @Override
    public BankMovementJpaEntity update(BankMovementJpaEntity jpaEntity) {
        return entityManager.merge(jpaEntity);
    }

    @Override
    public void deleteById(Long id) {
        BankMovementJpaEntity entity = entityManager.find(BankMovementJpaEntity.class, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public List<BankMovementJpaEntity> findByOriginCreditCardId(Long id) {
        return entityManager
                .createQuery(
                        "SELECT b FROM BankMovementJpaEntity b WHERE b.originCreditCard.id = :id ORDER BY b.timestamp DESC, b.id DESC",
                        BankMovementJpaEntity.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<BankMovementJpaEntity> findByBankAccountId(Long id) {
        return entityManager
                .createQuery(
                        "SELECT b FROM BankMovementJpaEntity b WHERE b.bankAccount.id = :id ORDER BY b.timestamp DESC, b.id DESC",
                        BankMovementJpaEntity.class)
                .setParameter("id", id)
                .getResultList();
    }
}
