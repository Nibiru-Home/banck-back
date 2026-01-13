package bank_back.bank_back.persistence.repository.impl;

import bank_back.bank_back.domain.model.BankAccount;
import bank_back.bank_back.domain.repository.BankAccountRepository;
import bank_back.bank_back.persistence.dao.jpa.BankAccountJpaDao;
import bank_back.bank_back.persistence.repository.mapper.BankAccountEntityMapper;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BankAccountRepositoryImpl implements BankAccountRepository {

    private final BankAccountJpaDao bankAccountJpaDao;

    public BankAccountRepositoryImpl(BankAccountJpaDao bankAccountJpaDao) {
        this.bankAccountJpaDao = bankAccountJpaDao;
    }

    @Override
    public List<BankAccount> findAll() {
        return bankAccountJpaDao.findAll(0, Integer.MAX_VALUE).stream()
                .map(BankAccountEntityMapper.getInstance()::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BankAccount> findById(Long id) {
        return bankAccountJpaDao.findById(id)
                .map(BankAccountEntityMapper.getInstance()::toModel);
    }

    @Override
    @Transactional
    public BankAccount save(BankAccount bankAccount) {
        if (bankAccount.getId() == null) {
            return BankAccountEntityMapper.getInstance().toModel(
                    bankAccountJpaDao.insert(BankAccountEntityMapper.getInstance().toEntity(bankAccount)));
        } else {
            return BankAccountEntityMapper.getInstance().toModel(
                    bankAccountJpaDao.update(BankAccountEntityMapper.getInstance().toEntity(bankAccount)));
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        bankAccountJpaDao.deleteById(id);
    }
}
