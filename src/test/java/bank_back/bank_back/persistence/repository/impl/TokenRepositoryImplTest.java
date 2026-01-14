package bank_back.bank_back.persistence.repository.impl;

import bank_back.bank_back.domain.model.Token;
import bank_back.bank_back.persistence.dao.jpa.TokenJpaDao;
import bank_back.bank_back.persistence.dao.jpa.entity.TokenJpaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TokenRepositoryImplTest {

    private TokenJpaDao tokenJpaDao;

    private TokenRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        tokenJpaDao = mock(TokenJpaDao.class);
        repository = new TokenRepositoryImpl(tokenJpaDao);
    }

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

}
