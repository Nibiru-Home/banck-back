package bank_back.bank_back.persistence.dao.jpa;

import bank_back.bank_back.persistence.dao.jpa.entity.BankMovementJpaEntity;

import java.util.List;

public interface BankMovementJpaDao extends GenericJpaDao<BankMovementJpaEntity, Long> {
    List<BankMovementJpaEntity> findByOriginCreditCardId(Long id);

    List<BankMovementJpaEntity> findByBankAccountId(Long id);
}
