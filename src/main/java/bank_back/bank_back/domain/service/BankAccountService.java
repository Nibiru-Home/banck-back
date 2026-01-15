package bank_back.bank_back.domain.service;

import java.util.List;
import java.util.UUID;

import bank_back.bank_back.domain.model.BankAccount;

public interface BankAccountService {
        List<BankAccount> findAll();

        BankAccount findById(Long id);

        BankAccount findByCreditCardId(Long id);

        List<BankAccount> findByClientId(UUID clientId);

        BankAccount create(BankAccount bankAccount);

        BankAccount update(Long id, BankAccount bankAccount);

        void delete(Long id);

        BankAccount findByIban(String iban);

        bank_back.bank_back.domain.dto.BankAccountDto retirar(
                        bank_back.bank_back.domain.dto.BankAccountDto cuentaBancariaDto,
                        bank_back.bank_back.domain.dto.CreditCardDto tarjetaCreditoDto, java.math.BigDecimal importe,
                        String concepto);

        bank_back.bank_back.domain.dto.BankAccountDto ingresar(
                        bank_back.bank_back.domain.dto.BankAccountDto cuentaBancariaDto,
                        bank_back.bank_back.domain.dto.CreditCardDto tarjetaCreditoDto, java.math.BigDecimal importe,
                        String concepto);
}
