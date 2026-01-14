package bank_back.bank_back.persistence.dao.jpa.impl;

import bank_back.bank_back.persistence.dao.jpa.entity.BankAccountJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BankAccountJpaDaoImplTest {

    private BankAccountJpaDaoImpl createDao(EntityManager entityManager) {
        BankAccountJpaDaoImpl dao = new BankAccountJpaDaoImpl();
        ReflectionTestUtils.setField(dao, "entityManager", entityManager);
        return dao;
    }

    @Test
    void findAll_ShouldReturnResults() {
        EntityManager entityManager = mock(EntityManager.class);
        TypedQuery<BankAccountJpaEntity> typedQuery = mock(TypedQuery.class);
        BankAccountJpaDaoImpl dao = createDao(entityManager);
        BankAccountJpaEntity entity = new BankAccountJpaEntity();

        when(entityManager.createQuery("SELECT b FROM BankAccountJpaEntity b", BankAccountJpaEntity.class))
                .thenReturn(typedQuery);
        when(typedQuery.setFirstResult(20)).thenReturn(typedQuery);
        when(typedQuery.setMaxResults(10)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(List.of(entity));

        List<BankAccountJpaEntity> result = dao.findAll(2, 10);

        assertEquals(1, result.size());
        verify(typedQuery).setFirstResult(20);
        verify(typedQuery).setMaxResults(10);
    }

    @Test
    void findById_ShouldReturnOptional_WhenEntityExists() {
        EntityManager entityManager = mock(EntityManager.class);
        BankAccountJpaDaoImpl dao = createDao(entityManager);
        BankAccountJpaEntity entity = new BankAccountJpaEntity();
        entity.setId(1L);

        when(entityManager.find(BankAccountJpaEntity.class, 1L)).thenReturn(entity);

        Optional<BankAccountJpaEntity> result = dao.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void findById_ShouldReturnEmpty_WhenEntityMissing() {
        EntityManager entityManager = mock(EntityManager.class);
        BankAccountJpaDaoImpl dao = createDao(entityManager);
        when(entityManager.find(BankAccountJpaEntity.class, 9L)).thenReturn(null);

        Optional<BankAccountJpaEntity> result = dao.findById(9L);

        assertTrue(result.isEmpty());
    }

    @Test
    void insert_ShouldPersistEntity() {
        EntityManager entityManager = mock(EntityManager.class);
        BankAccountJpaDaoImpl dao = createDao(entityManager);
        BankAccountJpaEntity entity = new BankAccountJpaEntity();

        BankAccountJpaEntity result = dao.insert(entity);

        verify(entityManager).persist(entity);
        assertSame(entity, result);
    }

    @Test
    void update_ShouldMergeEntity() {
        EntityManager entityManager = mock(EntityManager.class);
        BankAccountJpaDaoImpl dao = createDao(entityManager);
        BankAccountJpaEntity entity = new BankAccountJpaEntity();
        BankAccountJpaEntity merged = new BankAccountJpaEntity();

        when(entityManager.merge(entity)).thenReturn(merged);

        BankAccountJpaEntity result = dao.update(entity);

        verify(entityManager).merge(entity);
        assertSame(merged, result);
    }

    @Test
    void deleteById_ShouldRemoveEntity_WhenFound() {
        EntityManager entityManager = mock(EntityManager.class);
        BankAccountJpaDaoImpl dao = createDao(entityManager);
        BankAccountJpaEntity entity = new BankAccountJpaEntity();
        entity.setId(3L);

        when(entityManager.find(BankAccountJpaEntity.class, 3L)).thenReturn(entity);

        dao.deleteById(3L);

        verify(entityManager).remove(entity);
    }

    @Test
    void deleteById_ShouldNotRemove_WhenMissing() {
        EntityManager entityManager = mock(EntityManager.class);
        BankAccountJpaDaoImpl dao = createDao(entityManager);
        when(entityManager.find(BankAccountJpaEntity.class, 4L)).thenReturn(null);

        dao.deleteById(4L);

        verify(entityManager, never()).remove(any(BankAccountJpaEntity.class));
    }

}
