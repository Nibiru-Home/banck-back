package bank_back.bank_back.domain.mapper;

import bank_back.bank_back.domain.dto.BankAccountDto;
import bank_back.bank_back.domain.model.BankAccount;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BankAccountMapper {

        private final ClientMapper clientMapper;
        private final BankMovementMapper bankMovementMapper;
        private final CreditCardMapper creditCardMapper;

        public BankAccountMapper(ClientMapper clientMapper, BankMovementMapper bankMovementMapper,
                        CreditCardMapper creditCardMapper) {
                this.clientMapper = clientMapper;
                this.bankMovementMapper = bankMovementMapper;
                this.creditCardMapper = creditCardMapper;
        }

        public BankAccountDto toDto(BankAccount model) {
                if (model == null) {
                        return null;
                }
                return new BankAccountDto(
                                model.getId(),
                                model.getBalance(),
                                model.getIban(),
                                clientMapper.toDto(model.getClient()),
                                model.getMovements() != null
                                                ? model.getMovements().stream().map(bankMovementMapper::toDto)
                                                                .collect(Collectors.toList())
                                                : null,
                                model.getCreditCards() != null
                                                ? model.getCreditCards().stream().map(creditCardMapper::toDto)
                                                                .collect(Collectors.toList())
                                                : null);
        }

        public BankAccount toModel(BankAccountDto dto) {
                if (dto == null) {
                        return null;
                }
                BankAccount model = new BankAccount();
                model.setId(dto.id());
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
