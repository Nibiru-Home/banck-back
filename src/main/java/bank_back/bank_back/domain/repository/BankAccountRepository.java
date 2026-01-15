package bank_back.bank_back.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import bank_back.bank_back.domain.model.BankAccount;

public interface BankAccountRepository {
    List<BankAccount> findAll();

    Optional<BankAccount> findById(Long id);

    Optional<BankAccount> findByCreditCardId(Long id);

    List<BankAccount> findByClientId(UUID clientId);

    BankAccount save(BankAccount bankAccount);

    void deleteById(Long id);
}
