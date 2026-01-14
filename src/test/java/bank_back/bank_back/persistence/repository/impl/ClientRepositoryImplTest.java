package bank_back.bank_back.persistence.repository.impl;

import bank_back.bank_back.domain.model.Client;
import bank_back.bank_back.persistence.dao.jpa.ClientJpaDao;
import bank_back.bank_back.persistence.dao.jpa.entity.ClientJpaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClientRepositoryImplTest {

    private ClientJpaDao clientJpaDao;

    private ClientRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        clientJpaDao = mock(ClientJpaDao.class);
        repository = new ClientRepositoryImpl(clientJpaDao);
    }

    @Test
    void findAll_ShouldMapEntities() {
        ClientJpaEntity entity = new ClientJpaEntity();
        UUID id = UUID.randomUUID();
        entity.setId(id);
        entity.setLogin("marta");

        when(clientJpaDao.findAll(0, Integer.MAX_VALUE)).thenReturn(List.of(entity));

        List<Client> result = repository.findAll();

        assertEquals(1, result.size());
        assertEquals(id, result.get(0).getId());
        assertEquals(entity.getLogin(), result.get(0).getLogin());
        verify(clientJpaDao).findAll(0, Integer.MAX_VALUE);
    }

    @Test
    void findById_ShouldMapEntity_WhenExists() {
        UUID id = UUID.randomUUID();
        ClientJpaEntity entity = new ClientJpaEntity();
        entity.setId(id);
        entity.setLogin("ana");

        when(clientJpaDao.findById(id)).thenReturn(Optional.of(entity));

        Optional<Client> result = repository.findById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        assertEquals(entity.getLogin(), result.get().getLogin());
    }

    @Test
    void findById_ShouldReturnEmpty_WhenMissing() {
        UUID id = UUID.randomUUID();
        when(clientJpaDao.findById(id)).thenReturn(Optional.empty());

        Optional<Client> result = repository.findById(id);

        assertTrue(result.isEmpty());
    }

    @Test
    void save_ShouldInsert_WhenIdIsNull() {
        Client model = new Client();
        model.setLogin("marta");
        model.setDNI("12345678A");

        ClientJpaEntity savedEntity = new ClientJpaEntity();
        UUID savedId = UUID.randomUUID();
        savedEntity.setId(savedId);
        savedEntity.setLogin(model.getLogin());
        savedEntity.setDNI(model.getDNI());

        when(clientJpaDao.insert(any(ClientJpaEntity.class))).thenReturn(savedEntity);

        Client result = repository.save(model);

        ArgumentCaptor<ClientJpaEntity> captor = ArgumentCaptor.forClass(ClientJpaEntity.class);
        verify(clientJpaDao).insert(captor.capture());
        verify(clientJpaDao, never()).update(any(ClientJpaEntity.class));

        ClientJpaEntity captured = captor.getValue();
        assertNull(captured.getId());
        assertEquals(model.getLogin(), captured.getLogin());
        assertEquals(model.getDNI(), captured.getDNI());

        assertEquals(savedId, result.getId());
    }

    @Test
    void save_ShouldUpdate_WhenIdIsNotNull() {
        UUID id = UUID.randomUUID();
        Client model = new Client();
        model.setId(id);
        model.setLogin("luis");

        ClientJpaEntity updatedEntity = new ClientJpaEntity();
        updatedEntity.setId(id);
        updatedEntity.setLogin(model.getLogin());

        when(clientJpaDao.update(any(ClientJpaEntity.class))).thenReturn(updatedEntity);

        Client result = repository.save(model);

        verify(clientJpaDao).update(any(ClientJpaEntity.class));
        verify(clientJpaDao, never()).insert(any(ClientJpaEntity.class));
        assertEquals(id, result.getId());
        assertEquals(model.getLogin(), result.getLogin());
    }

    @Test
    void deleteById_ShouldDelegateToDao() {
        UUID id = UUID.randomUUID();
        repository.deleteById(id);

        verify(clientJpaDao).deleteById(id);
    }
}
