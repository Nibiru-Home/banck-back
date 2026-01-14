package bank_back.bank_back.persistence.repository.mapper;

import bank_back.bank_back.domain.model.Token;
import bank_back.bank_back.persistence.dao.jpa.entity.TokenJpaEntity;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TokenMapperTest {

    private final TokenMapper mapper = TokenMapper.getInstance();

    @Test
    void toToken_ShouldMapFields_WhenEntityIsNotNull() {
        UUID id = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        Instant createdAt = Instant.parse("2025-01-01T00:00:00Z");

        TokenJpaEntity entity = new TokenJpaEntity(id, "token", clientId, createdAt);

        Token token = mapper.toToken(entity);

        assertNotNull(token);
        assertEquals(id, token.getId());
        assertEquals("token", token.getValue());
        assertEquals(clientId, token.getClientId());
        assertEquals(createdAt, token.getCreatedAt());
    }

    @Test
    void toToken_ShouldReturnNull_WhenEntityIsNull() {
        assertNull(mapper.toToken(null));
    }

    @Test
    void toTokenJpaEntity_ShouldMapFields_WhenTokenIsNotNull() {
        UUID id = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        Instant createdAt = Instant.parse("2025-02-01T00:00:00Z");

        Token token = new Token(id, "token2", clientId, createdAt);

        TokenJpaEntity entity = mapper.toTokenJpaEntity(token);

        assertNotNull(entity);
        assertEquals(id, entity.getId());
        assertEquals("token2", entity.getValue());
        assertEquals(clientId, entity.getClientId());
        assertEquals(createdAt, entity.getCreatedAt());
    }

    @Test
    void toTokenJpaEntity_ShouldReturnNull_WhenTokenIsNull() {
        assertNull(mapper.toTokenJpaEntity(null));
    }
}
