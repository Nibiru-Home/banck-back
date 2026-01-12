package bank_back.bank_back.controller;

import bank_back.bank_back.domain.dto.ClientDto;
import bank_back.bank_back.controller.mapper.ClientMapper;
import bank_back.bank_back.domain.model.Client;
import bank_back.bank_back.domain.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    public ClientController(ClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> findAll() {
        List<Client> clients = clientService.findAll();
        List<ClientDto> dtos = clients.stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable Long id) {
        Client client = clientService.findById(id);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clientMapper.toDto(client));
    }

    @PostMapping
    public ResponseEntity<ClientDto> create(@RequestBody ClientDto dto) {
        Client client = clientMapper.toModel(dto);
        Client created = clientService.create(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientMapper.toDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable Long id, @RequestBody ClientDto dto) {
        Client client = clientMapper.toModel(dto);
        Client updated = clientService.update(id, client);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clientMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
