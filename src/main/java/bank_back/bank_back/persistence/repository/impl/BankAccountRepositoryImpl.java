package bank_back.bank_back.persistence.repository.impl;

import bank_back.bank_back.domain.model.BankAccount;
import bank_back.bank_back.domain.repository.BankAccountRepository;
import bank_back.bank_back.persistence.dao.jpa.BankAccountJpaDao;
import bank_back.bank_back.persistence.repository.mapper.BankAccountEntityMapper;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class BankAccountRepositoryImpl implements BankAccountRepository {

    private final BankAccountJpaDao bankAccountJpaDao;
    private final bank_back.bank_back.persistence.dao.jpa.BankMovementJpaDao bankMovementJpaDao;
    private final bank_back.bank_back.persistence.dao.jpa.CreditCardJpaDao creditCardJpaDao;

    public BankAccountRepositoryImpl(BankAccountJpaDao bankAccountJpaDao,
            bank_back.bank_back.persistence.dao.jpa.BankMovementJpaDao bankMovementJpaDao,
            bank_back.bank_back.persistence.dao.jpa.CreditCardJpaDao creditCardJpaDao) {
        this.bankAccountJpaDao = bankAccountJpaDao;
        this.bankMovementJpaDao = bankMovementJpaDao;
        this.creditCardJpaDao = creditCardJpaDao;
    }

    @Override
    public List<BankAccount> findAll() {
        return bankAccountJpaDao.findAll(0, Integer.MAX_VALUE).stream()
                .map(BankAccountEntityMapper.getInstance()::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BankAccount> findById(Long id) {
        return bankAccountJpaDao.findById(id)
                .map(BankAccountEntityMapper.getInstance()::toModel);
    }

    @Override
    @Transactional
    public BankAccount save(BankAccount bankAccount) {
        if (bankAccount.getId() == null) {
            return BankAccountEntityMapper.getInstance().toModel(
                    bankAccountJpaDao.insert(BankAccountEntityMapper.getInstance().toEntity(bankAccount)));
        } else {
            return BankAccountEntityMapper.getInstance().toModel(
                    bankAccountJpaDao.update(BankAccountEntityMapper.getInstance().toEntity(bankAccount)));
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        bankAccountJpaDao.deleteById(id);
    }

    @Override
    public Optional<BankAccount> findByCreditCardId(Long id) {
        return bankAccountJpaDao.findByCreditCardId(id)
                .map(BankAccountEntityMapper.getInstance()::toModel);
    }

    @Override
    public List<BankAccount> findByClientId(UUID clientId) {
        return bankAccountJpaDao.findByClientId(clientId).stream()
                .map(BankAccountEntityMapper.getInstance()::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BankAccount> findByIban(String iban) {
        return bankAccountJpaDao.findByIban(iban)
                .map(BankAccountEntityMapper.getInstance()::toModel);
    }

    @Override
    @Transactional
    public bank_back.bank_back.domain.dto.BankAccountDto retirar(
            bank_back.bank_back.domain.dto.BankAccountDto cuentaBancariaDto,
            bank_back.bank_back.domain.dto.CreditCardDto tarjetaCreditoDto, java.math.BigDecimal importe,
            String concepto) {
        bank_back.bank_back.persistence.dao.jpa.entity.BankAccountJpaEntity managedEntity = bankAccountJpaDao
                .findById(cuentaBancariaDto.id())
                .orElseThrow(() -> new bank_back.bank_back.domain.exception.BusinessException("Account not found"));

        managedEntity.setBalance(managedEntity.getBalance().subtract(importe));
        bank_back.bank_back.persistence.dao.jpa.entity.BankAccountJpaEntity updatedEntity = bankAccountJpaDao
                .update(managedEntity);

        bank_back.bank_back.persistence.dao.jpa.entity.BankMovementJpaEntity movimiento = new bank_back.bank_back.persistence.dao.jpa.entity.BankMovementJpaEntity();
        movimiento.setMovementType(bank_back.bank_back.domain.model.MovementType.Remove);
        movimiento.setMovementOrigin(bank_back.bank_back.domain.model.MovementOrigin.Bank_card);
        movimiento.setAmount(importe);
        movimiento.setTimestamp(java.time.LocalDateTime.now());
        movimiento.setConcept((concepto == null || concepto.trim().isEmpty()) ? "Reintegro" : concepto);
        movimiento.setBankAccount(updatedEntity);

        if (tarjetaCreditoDto != null) {
            bank_back.bank_back.persistence.dao.jpa.entity.CreditCardJpaEntity card = null;
            if (tarjetaCreditoDto.id() != null) {
                card = creditCardJpaDao.findById(tarjetaCreditoDto.id()).orElse(null);
            } else if (tarjetaCreditoDto.number() != null) {
                card = creditCardJpaDao.findByNumber(tarjetaCreditoDto.number()).orElse(null);
            }
            movimiento.setOriginCreditCard(card);
        }

        bankMovementJpaDao.insert(movimiento);

        bank_back.bank_back.domain.dto.ClientDto clientDto = new bank_back.bank_back.domain.dto.ClientDto(
                updatedEntity.getClient().getId(),
                updatedEntity.getClient().getLogin(),
                updatedEntity.getClient().getPassword(),
                updatedEntity.getClient().getFirstName(),
                updatedEntity.getClient().getLastName(),
                updatedEntity.getClient().getSecondLastName(),
                updatedEntity.getClient().getDNI(),
                updatedEntity.getClient().getApiToken(),
                null);
        return new bank_back.bank_back.domain.dto.BankAccountDto(
                updatedEntity.getId(),
                updatedEntity.getBalance(),
                updatedEntity.getIban(),
                clientDto,
                null,
                null);
    }

    @Override
    @Transactional
    public bank_back.bank_back.domain.dto.BankAccountDto ingresar(
            bank_back.bank_back.domain.dto.BankAccountDto cuentaBancariaDto,
            bank_back.bank_back.domain.dto.CreditCardDto tarjetaCreditoDto, java.math.BigDecimal importe,
            String concepto) {
        bank_back.bank_back.persistence.dao.jpa.entity.BankAccountJpaEntity managedEntity = bankAccountJpaDao
                .findById(cuentaBancariaDto.id())
                .orElseThrow(() -> new bank_back.bank_back.domain.exception.BusinessException("Account not found"));

        managedEntity.setBalance(managedEntity.getBalance().add(importe));
        bank_back.bank_back.persistence.dao.jpa.entity.BankAccountJpaEntity updatedEntity = bankAccountJpaDao
                .update(managedEntity);

        bank_back.bank_back.persistence.dao.jpa.entity.BankMovementJpaEntity movimiento = new bank_back.bank_back.persistence.dao.jpa.entity.BankMovementJpaEntity();
        movimiento.setMovementType(bank_back.bank_back.domain.model.MovementType.Add);
        movimiento.setMovementOrigin(bank_back.bank_back.domain.model.MovementOrigin.Bank_card);
        movimiento.setAmount(importe);
        movimiento.setTimestamp(java.time.LocalDateTime.now());
        movimiento.setConcept((concepto == null || concepto.trim().isEmpty()) ? "Ingreso" : concepto);
        movimiento.setBankAccount(updatedEntity);

        if (tarjetaCreditoDto != null) {
            bank_back.bank_back.persistence.dao.jpa.entity.CreditCardJpaEntity card = null;
            if (tarjetaCreditoDto.id() != null) {
                card = creditCardJpaDao.findById(tarjetaCreditoDto.id()).orElse(null);
            } else if (tarjetaCreditoDto.number() != null) {
                card = creditCardJpaDao.findByNumber(tarjetaCreditoDto.number()).orElse(null);
            }
            movimiento.setOriginCreditCard(card);
        }

        bankMovementJpaDao.insert(movimiento);

        bank_back.bank_back.domain.dto.ClientDto clientDto = new bank_back.bank_back.domain.dto.ClientDto(
                updatedEntity.getClient().getId(),
                updatedEntity.getClient().getLogin(),
                updatedEntity.getClient().getPassword(),
                updatedEntity.getClient().getFirstName(),
                updatedEntity.getClient().getLastName(),
                updatedEntity.getClient().getSecondLastName(),
                updatedEntity.getClient().getDNI(),
                updatedEntity.getClient().getApiToken(),
                null);
        return new bank_back.bank_back.domain.dto.BankAccountDto(
                updatedEntity.getId(),
                updatedEntity.getBalance(),
                updatedEntity.getIban(),
                clientDto,
                null,
                null);
    }
}
