package bank_back.bank_back.domain.repository;

import java.util.List;
import java.util.Optional;

import bank_back.bank_back.domain.model.BankAccount;

public interface BankAccountRepository {
    List<BankAccount> findAll();

    Optional<BankAccount> findById(Long id);

    BankAccount save(BankAccount bankAccount);

    void deleteById(Long id);
}
