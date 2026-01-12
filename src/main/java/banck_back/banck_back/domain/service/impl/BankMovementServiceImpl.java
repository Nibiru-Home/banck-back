package banck_back.banck_back.domain.service.impl;

import banck_back.banck_back.domain.model.BankMovement;
import banck_back.banck_back.domain.repository.BankMovementRepository;
import banck_back.banck_back.domain.service.BankMovementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankMovementServiceImpl implements BankMovementService {

    private final BankMovementRepository bankMovementRepository;

    public BankMovementServiceImpl(BankMovementRepository bankMovementRepository) {
        this.bankMovementRepository = bankMovementRepository;
    }

    @Override
    public List<BankMovement> findAll() {
        return bankMovementRepository.findAll();
    }

    @Override
    public BankMovement findById(Long id) {
        return bankMovementRepository.findById(id).orElse(null);
    }

    @Override
    public BankMovement create(BankMovement bankMovement) {
        return bankMovementRepository.save(bankMovement);
    }

    @Override
    public BankMovement update(Long id, BankMovement bankMovement) {
        if (bankMovementRepository.findById(id).isEmpty()) {
            return null;
        }
        bankMovement.setId(id);
        return bankMovementRepository.save(bankMovement);
    }

    @Override
    public void delete(Long id) {
        bankMovementRepository.deleteById(id);
    }
}
