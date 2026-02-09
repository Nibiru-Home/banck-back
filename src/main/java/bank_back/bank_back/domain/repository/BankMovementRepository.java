package bank_back.bank_back.domain.repository;

import java.util.List;
import java.util.Optional;

import bank_back.bank_back.domain.model.BankMovement;

public interface BankMovementRepository {
    List<BankMovement> findAll();

    Optional<BankMovement> findById(Long id);

    List<BankMovement> findByCreditCardId(Long creditCardId);

    List<BankMovement> findByBankAccountId(Long bankAccountId);

    BankMovement save(BankMovement bankMovement);

    void deleteById(Long id);
}
