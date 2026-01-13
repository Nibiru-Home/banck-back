package bank_back.bank_back.domain.model;

import java.time.Instant;
import java.util.UUID;

public class Token {

    private UUID id;
    private String value;
    private UUID clientId;
    private Instant createdAt;


    public Token(UUID id, String value, UUID clientId,
                 Instant createdAt) {
        this.id = id;
        this.value = value;
        this.clientId = clientId;
        this.createdAt = createdAt;
    }

    public boolean isExpired() {
        return Instant.now().isAfter(createdAt);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Token [id=" + id + ", value=" + value + ", clientId=" + clientId + ", createdAt=" + createdAt + "]";
    }
}

