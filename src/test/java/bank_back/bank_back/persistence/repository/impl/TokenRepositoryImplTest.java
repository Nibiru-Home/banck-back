package bank_back.bank_back.persistence.repository.impl;

import bank_back.bank_back.domain.model.Token;
import bank_back.bank_back.persistence.dao.jpa.TokenJpaDao;
import bank_back.bank_back.persistence.dao.jpa.entity.TokenJpaEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TokenRepositoryImplTest {

    @Mock
    private TokenJpaDao tokenJpaDao;

    @InjectMocks
    private TokenRepositoryImpl repository;

    @Test
    void save_ShouldInsert_WhenIdIsNull() {
        UUID clientId = UUID.randomUUID();
        Instant createdAt = Instant.parse("2025-01-01T00:00:00Z");
        Token token = new Token(null, "token", clientId, createdAt);

        repository.save(token);

        ArgumentCaptor<TokenJpaEntity> captor = ArgumentCaptor.forClass(TokenJpaEntity.class);
        verify(tokenJpaDao).insert(captor.capture());
        verify(tokenJpaDao, never()).update(any(TokenJpaEntity.class));

        TokenJpaEntity captured = captor.getValue();
        assertNull(captured.getId());
        assertEquals(token.getValue(), captured.getValue());
        assertEquals(token.getClientId(), captured.getClientId());
        assertEquals(token.getCreatedAt(), captured.getCreatedAt());
    }

    @Test
    void save_ShouldUpdate_WhenIdIsNotNull() {
        UUID id = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        Instant createdAt = Instant.parse("2025-01-02T00:00:00Z");
        Token token = new Token(id, "token2", clientId, createdAt);

        repository.save(token);

        verify(tokenJpaDao).update(any(TokenJpaEntity.class));
        verify(tokenJpaDao, never()).insert(any(TokenJpaEntity.class));
    }

    @Test
    void findByValue_ShouldMapEntity_WhenExists() {
        UUID id = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        Instant createdAt = Instant.parse("2025-01-03T00:00:00Z");
        TokenJpaEntity entity = new TokenJpaEntity(id, "token3", clientId, createdAt);

        when(tokenJpaDao.findByValue("token3")).thenReturn(Optional.of(entity));

        Optional<Token> result = repository.findByValue("token3");

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        assertEquals("token3", result.get().getValue());
        assertEquals(clientId, result.get().getClientId());
        assertEquals(createdAt, result.get().getCreatedAt());
    }

    @Test
    void findByValue_ShouldReturnEmpty_WhenMissing() {
        when(tokenJpaDao.findByValue("missing")).thenReturn(Optional.empty());

        Optional<Token> result = repository.findByValue("missing");

        assertTrue(result.isEmpty());
    }
}
