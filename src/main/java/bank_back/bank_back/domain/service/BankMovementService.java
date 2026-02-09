package bank_back.bank_back.domain.service;

import java.util.List;

import bank_back.bank_back.domain.model.BankMovement;

public interface BankMovementService {
    List<BankMovement> findAll();

    List<BankMovement> findByCreditCardId(Long id);

    List<BankMovement> findByBankAccountId(Long id);

    BankMovement findById(Long id);

    BankMovement create(BankMovement bankMovement);

    BankMovement update(Long id, BankMovement bankMovement);

    void delete(Long id);
}
