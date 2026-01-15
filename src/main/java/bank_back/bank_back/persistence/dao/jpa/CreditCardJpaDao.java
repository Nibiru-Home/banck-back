package bank_back.bank_back.persistence.dao.jpa;

import bank_back.bank_back.persistence.dao.jpa.entity.CreditCardJpaEntity;
import java.util.Optional;

public interface CreditCardJpaDao extends GenericJpaDao<CreditCardJpaEntity, Long> {
    Optional<CreditCardJpaEntity> findByNumber(String number);

    java.util.List<CreditCardJpaEntity> findByClientId(java.util.UUID clientId);
}
