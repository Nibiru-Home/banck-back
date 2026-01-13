package bank_back.bank_back.controller.mapper;

import bank_back.bank_back.domain.dto.ClientDto;
import bank_back.bank_back.controller.webmodel.request.ClientRequest;
import bank_back.bank_back.controller.webmodel.response.ClientResponse;

public class ClientMapper {
    private static ClientMapper INSTANCE;

    private ClientMapper() {
    }

    public static ClientMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClientMapper();
        }
        return INSTANCE;
    }

    public ClientDto clientRequestToClientDto(ClientRequest clientRequest) {
        if (clientRequest == null) {
            return null;
        }

        return new ClientDto(
                clientRequest.id(),
                clientRequest.login(),
                clientRequest.password(),
                clientRequest.firstName(),
                clientRequest.lastName(),
                clientRequest.secondLastName(),
                clientRequest.DNI(),
                clientRequest.apiToken(),
                null);
    }

    public ClientResponse clientDtoToClientResponse(ClientDto clientDto) {
        if (clientDto == null) {
            return null;
        }

        return new ClientResponse(
                clientDto.id(),
                clientDto.login(),
                clientDto.firstName(),
                clientDto.lastName(),
                clientDto.secondLastName(),
                clientDto.DNI(),
                clientDto.apiToken(),
                clientDto.bankAccounts() != null
                        ? clientDto.bankAccounts().stream()
                                .map(BankAccountMapper.getInstance()::bankAccountDtoToBankAccountResponse)
                                .toList()
                        : null);
    }
}
