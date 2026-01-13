package bank_back.bank_back.persistence.repository.impl;

import bank_back.bank_back.domain.model.CreditCard;
import bank_back.bank_back.domain.repository.CreditCardRepository;
import bank_back.bank_back.persistence.dao.jpa.CreditCardJpaDao;
import bank_back.bank_back.persistence.repository.mapper.CreditCardEntityMapper;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CreditCardRepositoryImpl implements CreditCardRepository {

    private final CreditCardJpaDao creditCardJpaDao;

    public CreditCardRepositoryImpl(CreditCardJpaDao creditCardJpaDao) {
        this.creditCardJpaDao = creditCardJpaDao;
    }

    @Override
    public List<CreditCard> findAll() {
        return creditCardJpaDao.findAll(0, Integer.MAX_VALUE).stream()
                .map(CreditCardEntityMapper.getInstance()::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CreditCard> findById(Long id) {
        return creditCardJpaDao.findById(id)
                .map(CreditCardEntityMapper.getInstance()::toModel);
    }

    @Override
    @Transactional
    public CreditCard save(CreditCard creditCard) {
        if (creditCard.getId() == null) {
            return CreditCardEntityMapper.getInstance().toModel(
                    creditCardJpaDao.insert(CreditCardEntityMapper.getInstance().toEntity(creditCard)));
        } else {
            return CreditCardEntityMapper.getInstance().toModel(
                    creditCardJpaDao.update(CreditCardEntityMapper.getInstance().toEntity(creditCard)));
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        creditCardJpaDao.deleteById(id);
    }
}
