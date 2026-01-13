package bank_back.bank_back.persistence.dao.jpa.impl;

import bank_back.bank_back.persistence.dao.jpa.CreditCardJpaDao;
import bank_back.bank_back.persistence.dao.jpa.entity.CreditCardJpaEntity;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CreditCardJpaDaoImpl extends GenericJpaDaoImpl<CreditCardJpaEntity, Long> implements CreditCardJpaDao {

    public CreditCardJpaDaoImpl() {
        super(CreditCardJpaEntity.class);
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
