package bank_back.bank_back.persistence.dao.jpa.impl;

import bank_back.bank_back.persistence.dao.jpa.BankMovementJpaDao;
import bank_back.bank_back.persistence.dao.jpa.entity.BankMovementJpaEntity;
import org.springframework.stereotype.Repository;

@Repository
public class BankMovementJpaDaoImpl extends GenericJpaDaoImpl<BankMovementJpaEntity, Long>
        implements BankMovementJpaDao {

    public BankMovementJpaDaoImpl() {
        super(BankMovementJpaEntity.class);
    }
}
