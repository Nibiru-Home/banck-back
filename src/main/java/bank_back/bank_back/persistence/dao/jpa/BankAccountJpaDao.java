package bank_back.bank_back.persistence.dao.jpa;

import bank_back.bank_back.persistence.dao.jpa.entity.BankAccountJpaEntity;
import java.util.Optional;

public interface BankAccountJpaDao extends GenericJpaDao<BankAccountJpaEntity, Long> {
    Optional<BankAccountJpaEntity> findByIban(String iban);
}
