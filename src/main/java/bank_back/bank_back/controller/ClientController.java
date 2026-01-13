package bank_back.bank_back.controller;

import bank_back.bank_back.controller.mapper.ClientMapper;
import bank_back.bank_back.controller.webmodel.request.ClientRequest;
import bank_back.bank_back.controller.webmodel.response.ClientResponse;
import bank_back.bank_back.domain.dto.ClientDto;
import bank_back.bank_back.domain.model.Client;
import bank_back.bank_back.domain.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;
    private final bank_back.bank_back.domain.mapper.ClientMapper clientDomainMapper;

    public ClientController(ClientService clientService,
            bank_back.bank_back.domain.mapper.ClientMapper clientDomainMapper) {
        this.clientService = clientService;
        this.clientDomainMapper = clientDomainMapper;
    }

    @GetMapping
    public ResponseEntity<List<ClientResponse>> findAll() {
        List<Client> clients = clientService.findAll();
        List<ClientResponse> responses = clients.stream()
                .map(clientDomainMapper::toDto)
                .map(ClientMapper.getInstance()::clientDtoToClientResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> findById(@PathVariable Long id) {
        Client client = clientService.findById(id);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        ClientDto dto = clientDomainMapper.toDto(client);
        return ResponseEntity.ok(ClientMapper.getInstance().clientDtoToClientResponse(dto));
    }

    @PostMapping
    public ResponseEntity<ClientResponse> create(@RequestBody ClientRequest request) {
        ClientDto dto = ClientMapper.getInstance().clientRequestToClientDto(request);
        Client client = clientDomainMapper.toModel(dto);
        Client created = clientService.create(client);
        ClientDto createdDto = clientDomainMapper.toDto(created);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ClientMapper.getInstance().clientDtoToClientResponse(createdDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> update(@PathVariable Long id, @RequestBody ClientRequest request) {
        ClientDto dto = ClientMapper.getInstance().clientRequestToClientDto(request);
        Client client = clientDomainMapper.toModel(dto);
        Client updated = clientService.update(id, client);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        ClientDto updatedDto = clientDomainMapper.toDto(updated);
        return ResponseEntity.ok(ClientMapper.getInstance().clientDtoToClientResponse(updatedDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
