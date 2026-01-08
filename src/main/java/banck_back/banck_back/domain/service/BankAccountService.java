package banck_back.banck_back.domain.service;

import banck_back.banck_back.domain.model.BankAccount;
import java.util.List;

public interface BankAccountService {
    List<BankAccount> findAll();
    BankAccount findById(Long id);
    BankAccount create(BankAccount bankAccount);
    BankAccount update(Long id, BankAccount bankAccount);
    void delete(Long id);
}
