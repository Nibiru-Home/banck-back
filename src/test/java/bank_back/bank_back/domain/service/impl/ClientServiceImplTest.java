package bank_back.bank_back.domain.service.impl;

import bank_back.bank_back.domain.model.Client;
import bank_back.bank_back.domain.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    void findAll_ShouldReturnList() {
        Client client = new Client();
        UUID id = UUID.randomUUID();
        client.setId(id);
        when(clientRepository.findAll()).thenReturn(Collections.singletonList(client));

        List<Client> result = clientService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(id, result.get(0).getId());
        verify(clientRepository).findAll();
    }

    @Test
    void findById_ShouldReturnClient_WhenExists() {
        Client client = new Client();
        UUID id = UUID.randomUUID();
        client.setId(id);
        when(clientRepository.findById(id)).thenReturn(Optional.of(client));

        Client result = clientService.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(clientRepository).findById(id);
    }

    @Test
    void findById_ShouldReturnNull_WhenDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(clientRepository.findById(id)).thenReturn(Optional.empty());

        Client result = clientService.findById(id);

        assertNull(result);
        verify(clientRepository).findById(id);
    }

    @Test
    void create_ShouldReturnSavedClient() {
        Client client = new Client();
        UUID id = UUID.randomUUID();
        client.setId(id);
        when(clientRepository.save(client)).thenReturn(client);

        Client result = clientService.create(client);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(clientRepository).save(client);
    }

    @Test
    void update_ShouldReturnUpdatedClient_WhenExists() {
        Client client = new Client();
        UUID id = UUID.randomUUID();
        client.setId(id);

        when(clientRepository.findById(id)).thenReturn(Optional.of(client));
        when(clientRepository.save(client)).thenReturn(client);

        Client result = clientService.update(id, client);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(clientRepository).findById(id);
        verify(clientRepository).save(client);
    }

    @Test
    void update_ShouldReturnNull_WhenDoesNotExist() {
        Client client = new Client();
        UUID id = UUID.randomUUID();
        client.setId(id);
        when(clientRepository.findById(id)).thenReturn(Optional.empty());

        Client result = clientService.update(id, client);

        assertNull(result);
        verify(clientRepository).findById(id);
        verify(clientRepository, never()).save(client);
    }

    @Test
    void delete_ShouldCallRepositoryDelete() {
        UUID id = UUID.randomUUID();
        clientService.delete(id);

        verify(clientRepository).deleteById(id);
    }
}
