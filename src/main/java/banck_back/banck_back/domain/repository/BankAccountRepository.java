package banck_back.banck_back.domain.repository;

import banck_back.banck_back.domain.model.BankAccount;
import java.util.List;
import java.util.Optional;

public interface BankAccountRepository {
    List<BankAccount> findAll();

    Optional<BankAccount> findById(Long id);

    BankAccount save(BankAccount bankAccount);

    void deleteById(Long id);
}
