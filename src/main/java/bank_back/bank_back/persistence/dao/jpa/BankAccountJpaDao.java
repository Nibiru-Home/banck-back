package bank_back.bank_back.persistence.dao.jpa;

import bank_back.bank_back.persistence.dao.jpa.entity.BankAccountJpaEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BankAccountJpaDao extends GenericJpaDao<BankAccountJpaEntity, Long> {
    Optional<BankAccountJpaEntity> findByIban(String iban);

    Optional<BankAccountJpaEntity> findByCreditCardId(Long id);

    List<BankAccountJpaEntity> findByClientId(UUID clientId);
}
