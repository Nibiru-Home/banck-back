package bank_back.bank_back.persistence.dao.jpa.impl;

import bank_back.bank_back.persistence.dao.jpa.entity.ClientJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private CriteriaQuery<ClientJpaEntity> criteriaQuery;

    @Mock
    private Root<ClientJpaEntity> root;

    @Mock
    private TypedQuery<ClientJpaEntity> typedQuery;

    @InjectMocks
    private ClientJpaDaoImpl dao;

    @Test
    void findAll_ShouldReturnResults() {
        ClientJpaEntity entity = new ClientJpaEntity();

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(ClientJpaEntity.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(ClientJpaEntity.class)).thenReturn(root);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
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

    @Test
    void findByLogin_ShouldReturnEntity_WhenFound() {
        ClientJpaEntity entity = new ClientJpaEntity();
        entity.setLogin("marta");

        when(entityManager.createQuery(
                "SELECT c FROM ClientJpaEntity c WHERE c.login = :login",
                ClientJpaEntity.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter("login", "marta")).thenReturn(typedQuery);
        when(typedQuery.getSingleResult()).thenReturn(entity);

        Optional<ClientJpaEntity> result = dao.findByLogin("marta");

        assertTrue(result.isPresent());
        assertEquals("marta", result.get().getLogin());
    }

    @Test
    void findByLogin_ShouldReturnEmpty_WhenMissing() {
        when(entityManager.createQuery(
                "SELECT c FROM ClientJpaEntity c WHERE c.login = :login",
                ClientJpaEntity.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter("login", "missing")).thenReturn(typedQuery);
        when(typedQuery.getSingleResult()).thenThrow(new NoResultException());

        Optional<ClientJpaEntity> result = dao.findByLogin("missing");

        assertTrue(result.isEmpty());
    }

    @Test
    void findByDni_ShouldReturnEntity_WhenFound() {
        ClientJpaEntity entity = new ClientJpaEntity();
        entity.setDNI("12345678A");

        when(entityManager.createQuery(
                "SELECT c FROM ClientJpaEntity c WHERE c.DNI = :DNI",
                ClientJpaEntity.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter("DNI", "12345678A")).thenReturn(typedQuery);
        when(typedQuery.getSingleResult()).thenReturn(entity);

        Optional<ClientJpaEntity> result = dao.findByDNI("12345678A");

        assertTrue(result.isPresent());
        assertEquals("12345678A", result.get().getDNI());
    }

    @Test
    void findByDni_ShouldReturnEmpty_WhenMissing() {
        when(entityManager.createQuery(
                "SELECT c FROM ClientJpaEntity c WHERE c.DNI = :DNI",
                ClientJpaEntity.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter("DNI", "missing")).thenReturn(typedQuery);
        when(typedQuery.getSingleResult()).thenThrow(new NoResultException());

        Optional<ClientJpaEntity> result = dao.findByDNI("missing");

        assertTrue(result.isEmpty());
    }
}
