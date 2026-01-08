package banck_back.banck_back.domain.service;

import banck_back.banck_back.domain.model.BankMovement;
import java.util.List;

public interface BankMovementService {
    List<BankMovement> findAll();
    BankMovement findById(Long id);
    BankMovement create(BankMovement bankMovement);
    BankMovement update(Long id, BankMovement bankMovement);
    void delete(Long id);
}
