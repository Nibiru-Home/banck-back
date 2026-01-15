package bank_back.bank_back.domain.service;

import java.util.List;
import java.util.UUID;

import bank_back.bank_back.domain.model.BankAccount;

public interface BankAccountService {
    List<BankAccount> findAll();

    BankAccount findById(Long id);

    BankAccount findByCreditCardId(Long id);

    List<BankAccount> findByClientId(UUID clientId);

    BankAccount create(BankAccount bankAccount);

    BankAccount update(Long id, BankAccount bankAccount);

    void delete(Long id);
}
