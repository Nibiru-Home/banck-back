package bank_back.bank_back.persistence.dao.jpa.impl;

import bank_back.bank_back.persistence.dao.jpa.entity.TokenJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TokenJpaDaoImplTest {

    private EntityManager entityManager;

    private TypedQuery<TokenJpaEntity> typedQuery;

    private TokenJpaDaoImpl dao;

    @BeforeEach
    void setUp() {
        entityManager = mock(EntityManager.class);
        typedQuery = mock(TypedQuery.class);
        dao = new TokenJpaDaoImpl();
        ReflectionTestUtils.setField(dao, "entityManager", entityManager);
    }

    @Test
    void findAll_ShouldReturnResults() {
        TokenJpaEntity entity = new TokenJpaEntity();

        when(entityManager.createQuery("SELECT t FROM TokenJpaEntity t", TokenJpaEntity.class))
                .thenReturn(typedQuery);
        when(typedQuery.setFirstResult(0)).thenReturn(typedQuery);
        when(typedQuery.setMaxResults(5)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(List.of(entity));

        List<TokenJpaEntity> result = dao.findAll(1, 5);

        assertEquals(1, result.size());
        verify(typedQuery).setFirstResult(0);
        verify(typedQuery).setMaxResults(5);
    }

    @Test
    void findById_ShouldReturnOptional_WhenEntityExists() {
        UUID id = UUID.randomUUID();
        TokenJpaEntity entity = new TokenJpaEntity();
        entity.setId(id);

        when(entityManager.find(TokenJpaEntity.class, id)).thenReturn(entity);

        Optional<TokenJpaEntity> result = dao.findById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }

    @Test
    void findById_ShouldReturnEmpty_WhenEntityMissing() {
        UUID id = UUID.randomUUID();
        when(entityManager.find(TokenJpaEntity.class, id)).thenReturn(null);

        Optional<TokenJpaEntity> result = dao.findById(id);

        assertTrue(result.isEmpty());
    }

    @Test
    void insert_ShouldPersistEntity() {
        TokenJpaEntity entity = new TokenJpaEntity();

        TokenJpaEntity result = dao.insert(entity);

        verify(entityManager).persist(entity);
        assertSame(entity, result);
    }

    @Test
    void update_ShouldMergeEntity() {
        TokenJpaEntity entity = new TokenJpaEntity();
        TokenJpaEntity merged = new TokenJpaEntity();

        when(entityManager.merge(entity)).thenReturn(merged);

        TokenJpaEntity result = dao.update(entity);

        verify(entityManager).merge(entity);
        assertSame(merged, result);
    }

    @Test
    void deleteById_ShouldRemoveEntity_WhenFound() {
        UUID id = UUID.randomUUID();
        TokenJpaEntity entity = new TokenJpaEntity();
        entity.setId(id);

        when(entityManager.find(TokenJpaEntity.class, id)).thenReturn(entity);

        dao.deleteById(id);

        verify(entityManager).remove(entity);
    }

    @Test
    void deleteById_ShouldNotRemove_WhenMissing() {
        UUID id = UUID.randomUUID();
        when(entityManager.find(TokenJpaEntity.class, id)).thenReturn(null);

        dao.deleteById(id);

        verify(entityManager, never()).remove(any(TokenJpaEntity.class));
    }
}
