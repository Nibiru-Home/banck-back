package bank_back.bank_back.persistence.repository.impl;

import bank_back.bank_back.domain.model.BankMovement;
import bank_back.bank_back.domain.repository.BankMovementRepository;
import bank_back.bank_back.persistence.dao.jpa.BankMovementJpaDao;
import bank_back.bank_back.persistence.repository.mapper.BankMovementEntityMapper;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BankMovementRepositoryImpl implements BankMovementRepository {

    private final BankMovementJpaDao bankMovementJpaDao;

    public BankMovementRepositoryImpl(BankMovementJpaDao bankMovementJpaDao) {
        this.bankMovementJpaDao = bankMovementJpaDao;
    }

    @Override
    public List<BankMovement> findAll() {
        return bankMovementJpaDao.findAll(0, Integer.MAX_VALUE).stream()
                .map(BankMovementEntityMapper.getInstance()::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BankMovement> findById(Long id) {
        return bankMovementJpaDao.findById(id)
                .map(BankMovementEntityMapper.getInstance()::toModel);
    }

    @Override
    @Transactional
    public BankMovement save(BankMovement bankMovement) {
        if (bankMovement.getId() == null) {
            return BankMovementEntityMapper.getInstance().toModel(
                    bankMovementJpaDao.insert(BankMovementEntityMapper.getInstance().toEntity(bankMovement)));
        } else {
            return BankMovementEntityMapper.getInstance().toModel(
                    bankMovementJpaDao.update(BankMovementEntityMapper.getInstance().toEntity(bankMovement)));
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        bankMovementJpaDao.deleteById(id);
    }

    @Override
    public List<BankMovement> findByCreditCardId(Long creditCardId) {
        return bankMovementJpaDao.findByOriginCreditCardId(creditCardId).stream()
                .map(BankMovementEntityMapper.getInstance()::toModel)
                .collect(Collectors.toList());
    }
}
