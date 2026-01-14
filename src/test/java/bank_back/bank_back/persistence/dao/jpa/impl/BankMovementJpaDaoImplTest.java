package bank_back.bank_back.persistence.dao.jpa.impl;

import bank_back.bank_back.persistence.dao.jpa.entity.BankMovementJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BankMovementJpaDaoImplTest {

    private BankMovementJpaDaoImpl createDao(EntityManager entityManager) {
        BankMovementJpaDaoImpl dao = new BankMovementJpaDaoImpl();
        ReflectionTestUtils.setField(dao, "entityManager", entityManager);
        return dao;
    }

    @Test
    void findAll_ShouldReturnResults() {
        EntityManager entityManager = mock(EntityManager.class);
        TypedQuery<BankMovementJpaEntity> typedQuery = mock(TypedQuery.class);
        BankMovementJpaDaoImpl dao = createDao(entityManager);
        BankMovementJpaEntity entity = new BankMovementJpaEntity();

        when(entityManager.createQuery("SELECT b FROM BankMovementJpaEntity b", BankMovementJpaEntity.class))
                .thenReturn(typedQuery);
        when(typedQuery.setFirstResult(0)).thenReturn(typedQuery);
        when(typedQuery.setMaxResults(5)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(List.of(entity));

        List<BankMovementJpaEntity> result = dao.findAll(0, 5);

        assertEquals(1, result.size());
        verify(typedQuery).setFirstResult(0);
        verify(typedQuery).setMaxResults(5);
    }

    @Test
    void findById_ShouldReturnOptional_WhenEntityExists() {
        EntityManager entityManager = mock(EntityManager.class);
        BankMovementJpaDaoImpl dao = createDao(entityManager);
        BankMovementJpaEntity entity = new BankMovementJpaEntity();
        entity.setId(1L);

        when(entityManager.find(BankMovementJpaEntity.class, 1L)).thenReturn(entity);

        Optional<BankMovementJpaEntity> result = dao.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void findById_ShouldReturnEmpty_WhenEntityMissing() {
        EntityManager entityManager = mock(EntityManager.class);
        BankMovementJpaDaoImpl dao = createDao(entityManager);
        when(entityManager.find(BankMovementJpaEntity.class, 9L)).thenReturn(null);

        Optional<BankMovementJpaEntity> result = dao.findById(9L);

        assertTrue(result.isEmpty());
    }

    @Test
    void insert_ShouldPersistEntity() {
        EntityManager entityManager = mock(EntityManager.class);
        BankMovementJpaDaoImpl dao = createDao(entityManager);
        BankMovementJpaEntity entity = new BankMovementJpaEntity();

        BankMovementJpaEntity result = dao.insert(entity);

        verify(entityManager).persist(entity);
        assertSame(entity, result);
    }

    @Test
    void update_ShouldMergeEntity() {
        EntityManager entityManager = mock(EntityManager.class);
        BankMovementJpaDaoImpl dao = createDao(entityManager);
        BankMovementJpaEntity entity = new BankMovementJpaEntity();
        BankMovementJpaEntity merged = new BankMovementJpaEntity();

        when(entityManager.merge(entity)).thenReturn(merged);

        BankMovementJpaEntity result = dao.update(entity);

        verify(entityManager).merge(entity);
        assertSame(merged, result);
    }

    @Test
    void deleteById_ShouldRemoveEntity_WhenFound() {
        EntityManager entityManager = mock(EntityManager.class);
        BankMovementJpaDaoImpl dao = createDao(entityManager);
        BankMovementJpaEntity entity = new BankMovementJpaEntity();
        entity.setId(3L);

        when(entityManager.find(BankMovementJpaEntity.class, 3L)).thenReturn(entity);

        dao.deleteById(3L);

        verify(entityManager).remove(entity);
    }

    @Test
    void deleteById_ShouldNotRemove_WhenMissing() {
        EntityManager entityManager = mock(EntityManager.class);
        BankMovementJpaDaoImpl dao = createDao(entityManager);
        when(entityManager.find(BankMovementJpaEntity.class, 4L)).thenReturn(null);

        dao.deleteById(4L);

        verify(entityManager, never()).remove(any(BankMovementJpaEntity.class));
    }
}
