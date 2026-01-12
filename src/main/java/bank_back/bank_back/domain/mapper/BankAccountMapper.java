package bank_back.bank_back.domain.mapper;

import bank_back.bank_back.domain.dto.BankAccountDto;
import bank_back.bank_back.domain.model.BankAccount;

import java.util.stream.Collectors;

public class BankAccountMapper {

    private static BankAccountMapper INSTANCE;

    private BankAccountMapper() {
    }

    public static BankAccountMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BankAccountMapper();
        }
        return INSTANCE;
    }

    public BankAccountDto toDto(BankAccount model) {
        if (model == null) {
            return null;
        }
        return new BankAccountDto(
                null,
                model.getBalance(),
                model.getIban(),
                ClientMapper.getInstance().toDto(model.getClient()),
                model.getMovements() != null
                        ? model.getMovements().stream().map(BankMovementMapper.getInstance()::toDto)
                                .collect(Collectors.toList())
                        : null,
                model.getCreditCards() != null
                        ? model.getCreditCards().stream().map(CreditCardMapper.getInstance()::toDto)
                                .collect(Collectors.toList())
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
                ? dto.movements().stream().map(BankMovementMapper.getInstance()::toModel).collect(Collectors.toList())
                : null);
        model.setCreditCards(dto.creditCards() != null
                ? dto.creditCards().stream().map(CreditCardMapper.getInstance()::toModel).collect(Collectors.toList())
                : null);
        return model;
    }
}
