package bank_back.bank_back.domain.service;

import java.util.List;

import bank_back.bank_back.domain.model.CreditCard;

public interface CreditCardService {
    List<CreditCard> findAll();

    CreditCard findById(Long id);

    CreditCard create(CreditCard creditCard);

    CreditCard update(Long id, CreditCard creditCard);

    void delete(Long id);

    List<CreditCard> findByClientId(java.util.UUID clientId);

    bank_back.bank_back.domain.dto.CreditCardDto validate(bank_back.bank_back.domain.dto.CreditCardDto creditCardDto);
}
