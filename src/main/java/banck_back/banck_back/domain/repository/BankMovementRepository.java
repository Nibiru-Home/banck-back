package banck_back.banck_back.domain.repository;

import banck_back.banck_back.domain.model.BankMovement;
import java.util.List;
import java.util.Optional;

public interface BankMovementRepository {
    List<BankMovement> findAll();
    Optional<BankMovement> findById(Long id);
    BankMovement save(BankMovement bankMovement);
    void deleteById(Long id);
}
