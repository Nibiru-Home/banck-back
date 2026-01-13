package bank_back.bank_back.persistence.dao.jpa.impl;

import bank_back.bank_back.persistence.dao.jpa.BankAccountJpaDao;
import bank_back.bank_back.persistence.dao.jpa.entity.BankAccountJpaEntity;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BankAccountJpaDaoImpl extends GenericJpaDaoImpl<BankAccountJpaEntity, Long> implements BankAccountJpaDao {

    public BankAccountJpaDaoImpl() {
        super(BankAccountJpaEntity.class);
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
}
