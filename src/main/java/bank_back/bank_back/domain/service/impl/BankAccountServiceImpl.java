package bank_back.bank_back.domain.service.impl;

import bank_back.bank_back.domain.model.BankAccount;
import bank_back.bank_back.domain.repository.BankAccountRepository;
import bank_back.bank_back.domain.service.BankAccountService;

import java.util.List;
import java.util.UUID;

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
    public BankAccount findByCreditCardId(Long id) {
        return bankAccountRepository.findByCreditCardId(id).orElse(null);
    }

    @Override
    public List<BankAccount> findByClientId(UUID clientId) {
        return bankAccountRepository.findByClientId(clientId);
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

    @Override
    public BankAccount findByIban(String iban) {
        return bankAccountRepository.findByIban(iban).orElse(null);
    }

    @Override
    public bank_back.bank_back.domain.dto.BankAccountDto retirar(
            bank_back.bank_back.domain.dto.BankAccountDto cuentaBancariaDto,
            bank_back.bank_back.domain.dto.CreditCardDto tarjetaCreditoDto, java.math.BigDecimal importe,
            String concepto) {
        return bankAccountRepository.retirar(cuentaBancariaDto, tarjetaCreditoDto, importe, concepto);
    }

    @Override
    public bank_back.bank_back.domain.dto.BankAccountDto ingresar(
            bank_back.bank_back.domain.dto.BankAccountDto cuentaBancariaDto,
            bank_back.bank_back.domain.dto.CreditCardDto tarjetaCreditoDto, java.math.BigDecimal importe,
            String concepto) {
        return bankAccountRepository.ingresar(cuentaBancariaDto, tarjetaCreditoDto, importe, concepto);
    }
}
