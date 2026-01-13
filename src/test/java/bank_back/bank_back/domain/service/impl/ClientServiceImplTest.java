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
        client.setId(1L);
        when(clientRepository.findAll()).thenReturn(Collections.singletonList(client));

        List<Client> result = clientService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(clientRepository).findAll();
    }

    @Test
    void findById_ShouldReturnClient_WhenExists() {
        Client client = new Client();
        client.setId(1L);
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Client result = clientService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(clientRepository).findById(1L);
    }

    @Test
    void findById_ShouldReturnNull_WhenDoesNotExist() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        Client result = clientService.findById(1L);

        assertNull(result);
        verify(clientRepository).findById(1L);
    }

    @Test
    void create_ShouldReturnSavedClient() {
        Client client = new Client();
        client.setId(1L);
        when(clientRepository.save(client)).thenReturn(client);

        Client result = clientService.create(client);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(clientRepository).save(client);
    }

    @Test
    void update_ShouldReturnUpdatedClient_WhenExists() {
        Client client = new Client();
        client.setId(1L);

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(clientRepository.save(client)).thenReturn(client);

        Client result = clientService.update(1L, client);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(clientRepository).findById(1L);
        verify(clientRepository).save(client);
    }

    @Test
    void update_ShouldReturnNull_WhenDoesNotExist() {
        Client client = new Client();
        client.setId(1L);
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        Client result = clientService.update(1L, client);

        assertNull(result);
        verify(clientRepository).findById(1L);
        verify(clientRepository, never()).save(client);
    }

    @Test
    void delete_ShouldCallRepositoryDelete() {
        clientService.delete(1L);

        verify(clientRepository).deleteById(1L);
    }
}
