package bank_back.bank_back.controller.mapper;

import org.springframework.stereotype.Component;

import bank_back.bank_back.domain.dto.BankAccountDto;
import bank_back.bank_back.domain.model.BankAccount;

import java.util.stream.Collectors;

@Component
public class BankAccountMapper {

    private final BankMovementMapper bankMovementMapper;
    private final CreditCardMapper creditCardMapper;
    private final ClientMapper clientMapper;

    public BankAccountMapper(BankMovementMapper bankMovementMapper, CreditCardMapper creditCardMapper,
            @org.springframework.context.annotation.Lazy ClientMapper clientMapper) {
        this.bankMovementMapper = bankMovementMapper;
        this.creditCardMapper = creditCardMapper;
        this.clientMapper = clientMapper;
    }

    public BankAccountDto toDto(BankAccount model) {
        if (model == null) {
            return null;
        }
        return new BankAccountDto(
                null,
                model.getBalance(),
                model.getIban(),
                clientMapper.toDto(model.getClient()),
                model.getMovements() != null
                        ? model.getMovements().stream().map(bankMovementMapper::toDto).collect(Collectors.toList())
                        : null,
                model.getCreditCards() != null
                        ? model.getCreditCards().stream().map(creditCardMapper::toDto).collect(Collectors.toList())
                        : null);
    }

    public BankAccount toModel(BankAccountDto dto) {
        if (dto == null) {
            return null;
        }
        BankAccount model = new BankAccount();
        model.setBalance(dto.balance());
        model.setIban(dto.iban());
        model.setMovements(dto.movements() != null
                ? dto.movements().stream().map(bankMovementMapper::toModel).collect(Collectors.toList())
                : null);
        model.setCreditCards(dto.creditCards() != null
                ? dto.creditCards().stream().map(creditCardMapper::toModel).collect(Collectors.toList())
                : null);
        return model;
    }
}
