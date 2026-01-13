package bank_back.bank_back.controller.mapper;

import bank_back.bank_back.controller.webmodel.request.ClientRequest;
import java.util.UUID;
import bank_back.bank_back.controller.webmodel.response.ClientResponse;
import bank_back.bank_back.domain.dto.ClientDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientMapperTest {

    private final ClientMapper mapper = ClientMapper.getInstance();

    @Test
    void clientRequestToClientDto_ShouldReturnDto_WhenRequestIsValid() {
        ClientRequest request = new ClientRequest(
                UUID.randomUUID(),
                "user1",
                "pass123",
                "John",
                "Doe",
                "Smith",
                "12345678A",
                "token123");

        ClientDto dto = mapper.clientRequestToClientDto(request);

        assertNotNull(dto);
        assertEquals(request.id(), dto.id());
        assertEquals(request.login(), dto.login());
        assertEquals(request.password(), dto.password());
        assertEquals(request.firstName(), dto.firstName());
        assertEquals(request.lastName(), dto.lastName());
        assertEquals(request.secondLastName(), dto.secondLastName());
        assertEquals(request.DNI(), dto.DNI());
        assertEquals(request.apiToken(), dto.apiToken());
        assertNull(dto.bankAccounts());
    }

    @Test
    void clientRequestToClientDto_ShouldReturnNull_WhenRequestIsNull() {
        assertNull(mapper.clientRequestToClientDto(null));
    }

    @Test
    void clientDtoToClientResponse_ShouldReturnResponse_WhenDtoIsValid() {
        ClientDto dto = new ClientDto(
                UUID.randomUUID(),
                "user1",
                "pass123",
                "John",
                "Doe",
                "Smith",
                "12345678A",
                "token123",
                null);

        ClientResponse response = mapper.clientDtoToClientResponse(dto);

        assertNotNull(response);
        assertEquals(dto.id(), response.id());
        assertEquals(dto.login(), response.login());
        assertEquals(dto.firstName(), response.firstName());
        assertEquals(dto.lastName(), response.lastName());
        assertEquals(dto.secondLastName(), response.secondLastName());
        assertEquals(dto.DNI(), response.DNI());
        assertEquals(dto.apiToken(), response.apiToken());
        assertNull(response.bankAccounts());
    }

    @Test
    void clientDtoToClientResponse_ShouldReturnNull_WhenDtoIsNull() {
        assertNull(mapper.clientDtoToClientResponse(null));
    }
}
