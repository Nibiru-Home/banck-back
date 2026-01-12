package banck_back.banck_back.domain.service.impl;

import banck_back.banck_back.domain.model.BankAccount;
import banck_back.banck_back.domain.repository.BankAccountRepository;
import banck_back.banck_back.domain.service.BankAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public List<BankAccount> findAll() {
        return bankAccountRepository.findAll();
    }

    @Override
    public BankAccount findById(Long id) {
        return bankAccountRepository.findById(id).orElse(null);
    }

    @Override
    public BankAccount create(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public BankAccount update(Long id, BankAccount bankAccount) {
        if (bankAccountRepository.findById(id).isEmpty()) {
            return null;
        }
        bankAccount.setId(id);
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public void delete(Long id) {
        bankAccountRepository.deleteById(id);
    }
}
