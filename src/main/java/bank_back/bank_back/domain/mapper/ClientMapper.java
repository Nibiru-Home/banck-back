package bank_back.bank_back.domain.mapper;

import org.springframework.stereotype.Component;

import bank_back.bank_back.domain.dto.ClientDto;
import bank_back.bank_back.domain.model.Client;

import java.util.stream.Collectors;

@Component
public class ClientMapper {

    private final BankAccountMapper bankAccountMapper;

    public ClientMapper(BankAccountMapper bankAccountMapper) {
        this.bankAccountMapper = bankAccountMapper;
    }

    public ClientDto toDto(Client model) {
        if (model == null) {
            return null;
        }
        return new ClientDto(
                model.getId(),
                model.getLogin(),
                model.getFirstName(),
                model.getLastName(),
                model.getSecondLastName(),
                model.getNationalId(),
                model.getBankAccounts() != null
                        ? model.getBankAccounts().stream().map(bankAccountMapper::toDto).collect(Collectors.toList())
                        : null);
    }

    public Client toModel(ClientDto dto) {
        if (dto == null) {
            return null;
        }
        Client model = new Client();
        model.setId(dto.id());
        model.setLogin(dto.login());
        model.setFirstName(dto.firstName());
        model.setLastName(dto.lastName());
        model.setSecondLastName(dto.secondLastName());
        model.setNationalId(dto.nationalId());

        model.setBankAccounts(dto.bankAccounts() != null
                ? dto.bankAccounts().stream().map(bankAccountMapper::toModel).collect(Collectors.toList())
                : null);
        return model;
    }
}
