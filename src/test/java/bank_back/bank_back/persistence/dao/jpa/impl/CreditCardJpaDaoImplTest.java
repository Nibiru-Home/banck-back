package bank_back.bank_back.persistence.dao.jpa.impl;

import bank_back.bank_back.persistence.dao.jpa.entity.CreditCardJpaEntity;
import jakarta.persistence.EntityManager;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditCardJpaDaoImplTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private CriteriaQuery<CreditCardJpaEntity> criteriaQuery;

    @Mock
    private Root<CreditCardJpaEntity> root;

    @Mock
    private TypedQuery<CreditCardJpaEntity> typedQuery;

    @InjectMocks
    private CreditCardJpaDaoImpl dao;

    @Test
    void findAll_ShouldReturnResults() {
        CreditCardJpaEntity entity = new CreditCardJpaEntity();

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(CreditCardJpaEntity.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(CreditCardJpaEntity.class)).thenReturn(root);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(typedQuery.setFirstResult(0)).thenReturn(typedQuery);
        when(typedQuery.setMaxResults(10)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(List.of(entity));

        List<CreditCardJpaEntity> result = dao.findAll(0, 10);

        assertEquals(1, result.size());
        verify(typedQuery).setFirstResult(0);
        verify(typedQuery).setMaxResults(10);
    }

    @Test
    void findById_ShouldReturnOptional_WhenEntityExists() {
        CreditCardJpaEntity entity = new CreditCardJpaEntity();
        entity.setId(1L);

        when(entityManager.find(CreditCardJpaEntity.class, 1L)).thenReturn(entity);

        Optional<CreditCardJpaEntity> result = dao.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void findById_ShouldReturnEmpty_WhenEntityMissing() {
        when(entityManager.find(CreditCardJpaEntity.class, 9L)).thenReturn(null);

        Optional<CreditCardJpaEntity> result = dao.findById(9L);

        assertTrue(result.isEmpty());
    }

    @Test
    void insert_ShouldPersistEntity() {
        CreditCardJpaEntity entity = new CreditCardJpaEntity();

        CreditCardJpaEntity result = dao.insert(entity);

        verify(entityManager).persist(entity);
        assertSame(entity, result);
    }

    @Test
    void update_ShouldMergeEntity() {
        CreditCardJpaEntity entity = new CreditCardJpaEntity();
        CreditCardJpaEntity merged = new CreditCardJpaEntity();

        when(entityManager.merge(entity)).thenReturn(merged);

        CreditCardJpaEntity result = dao.update(entity);

        verify(entityManager).merge(entity);
        assertSame(merged, result);
    }

    @Test
    void deleteById_ShouldRemoveEntity_WhenFound() {
        CreditCardJpaEntity entity = new CreditCardJpaEntity();
        entity.setId(3L);

        when(entityManager.find(CreditCardJpaEntity.class, 3L)).thenReturn(entity);

        dao.deleteById(3L);

        verify(entityManager).remove(entity);
    }

    @Test
    void deleteById_ShouldNotRemove_WhenMissing() {
        when(entityManager.find(CreditCardJpaEntity.class, 4L)).thenReturn(null);

        dao.deleteById(4L);

        verify(entityManager, never()).remove(any(CreditCardJpaEntity.class));
    }

}
