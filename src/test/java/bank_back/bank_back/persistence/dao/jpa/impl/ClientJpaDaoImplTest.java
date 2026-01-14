package bank_back.bank_back.persistence.dao.jpa.impl;

import bank_back.bank_back.persistence.dao.jpa.entity.ClientJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientJpaDaoImplTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<ClientJpaEntity> typedQuery;

    @InjectMocks
    private ClientJpaDaoImpl dao;

    @Test
    void findAll_ShouldReturnResults() {
        ClientJpaEntity entity = new ClientJpaEntity();

        when(entityManager.createQuery("SELECT c FROM ClientJpaEntity c", ClientJpaEntity.class))
                .thenReturn(typedQuery);
        when(typedQuery.setFirstResult(10)).thenReturn(typedQuery);
        when(typedQuery.setMaxResults(5)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(List.of(entity));

        List<ClientJpaEntity> result = dao.findAll(2, 5);

        assertEquals(1, result.size());
        verify(typedQuery).setFirstResult(10);
        verify(typedQuery).setMaxResults(5);
    }

    @Test
    void findById_ShouldReturnOptional_WhenEntityExists() {
        UUID id = UUID.randomUUID();
        ClientJpaEntity entity = new ClientJpaEntity();
        entity.setId(id);

        when(entityManager.find(ClientJpaEntity.class, id)).thenReturn(entity);

        Optional<ClientJpaEntity> result = dao.findById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }

    @Test
    void findById_ShouldReturnEmpty_WhenEntityMissing() {
        UUID id = UUID.randomUUID();
        when(entityManager.find(ClientJpaEntity.class, id)).thenReturn(null);

        Optional<ClientJpaEntity> result = dao.findById(id);

        assertTrue(result.isEmpty());
    }

    @Test
    void insert_ShouldPersistEntity() {
        ClientJpaEntity entity = new ClientJpaEntity();

        ClientJpaEntity result = dao.insert(entity);

        verify(entityManager).persist(entity);
        assertSame(entity, result);
    }

    @Test
    void update_ShouldMergeEntity() {
        ClientJpaEntity entity = new ClientJpaEntity();
        ClientJpaEntity merged = new ClientJpaEntity();

        when(entityManager.merge(entity)).thenReturn(merged);

        ClientJpaEntity result = dao.update(entity);

        verify(entityManager).merge(entity);
        assertSame(merged, result);
    }

    @Test
    void deleteById_ShouldRemoveEntity_WhenFound() {
        UUID id = UUID.randomUUID();
        ClientJpaEntity entity = new ClientJpaEntity();
        entity.setId(id);

        when(entityManager.find(ClientJpaEntity.class, id)).thenReturn(entity);

        dao.deleteById(id);

        verify(entityManager).remove(entity);
    }

    @Test
    void deleteById_ShouldNotRemove_WhenMissing() {
        UUID id = UUID.randomUUID();
        when(entityManager.find(ClientJpaEntity.class, id)).thenReturn(null);

        dao.deleteById(id);

        verify(entityManager, never()).remove(any(ClientJpaEntity.class));
    }

}
