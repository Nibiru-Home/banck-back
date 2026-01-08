package banck_back.banck_back.domain.mapper;

import banck_back.banck_back.domain.dto.BankAccountDto;
import banck_back.banck_back.domain.model.BankAccount;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BankAccountMapper {

    private final BankMovementMapper bankMovementMapper;
    private final CreditCardMapper creditCardMapper;

    public BankAccountMapper(BankMovementMapper bankMovementMapper, CreditCardMapper creditCardMapper) {
        this.bankMovementMapper = bankMovementMapper;
        this.creditCardMapper = creditCardMapper;
    }

    public BankAccountDto toDto(BankAccount model) {
        if (model == null) {
            return null;
        }
        return new BankAccountDto(
                null, // Model has no ID
                model.getBalance(),
                model.getIban(),
                model.getClient() != null ? model.getClient().getId() : null,
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
        // model.setId(dto.id()); // Model has no ID
        model.setBalance(dto.balance());
        model.setIban(dto.iban());
        // Client relationship handled elsewhere
        model.setMovements(dto.movements() != null
                ? dto.movements().stream().map(bankMovementMapper::toModel).collect(Collectors.toList())
                : null);
        model.setCreditCards(dto.creditCards() != null
                ? dto.creditCards().stream().map(creditCardMapper::toModel).collect(Collectors.toList())
                : null);
        return model;
    }
}
