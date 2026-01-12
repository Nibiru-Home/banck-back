package bank_back.bank_back.domain.repository;

import java.util.List;
import java.util.Optional;

import bank_back.bank_back.domain.model.CreditCard;

public interface CreditCardRepository {
    List<CreditCard> findAll();
    Optional<CreditCard> findById(Long id);
    CreditCard save(CreditCard creditCard);
    void deleteById(Long id);
}
