package bank_back.bank_back.domain.mapper;

import bank_back.bank_back.domain.dto.ClientDto;
import bank_back.bank_back.domain.model.Client;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ClientMapper {

    private final BankAccountMapper bankAccountMapper;

    public ClientMapper(@Lazy BankAccountMapper bankAccountMapper) {
        this.bankAccountMapper = bankAccountMapper;
    }

    public ClientDto toDto(Client model) {
        if (model == null) {
            return null;
        }
        return new ClientDto(
                model.getId(),
                model.getLogin(),
                model.getPassword(),
                model.getFirstName(),
                model.getLastName(),
                model.getSecondLastName(),
                model.getDNI(),
                model.getApiToken(),
                model.getBankAccounts() != null
                        ? model.getBankAccounts().stream().map(bankAccountMapper::toDto)
                                .collect(Collectors.toList())
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
        model.setDNI(dto.DNI());

        model.setBankAccounts(dto.bankAccounts() != null
                ? dto.bankAccounts().stream().map(bankAccountMapper::toModel).collect(Collectors.toList())
                : null);
        return model;
    }
}
