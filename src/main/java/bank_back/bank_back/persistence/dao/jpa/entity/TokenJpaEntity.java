package bank_back.bank_back.persistence.dao.jpa.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "sesions")
public class TokenJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    @Column(name = "token_value", nullable = false, unique = true, length = 255)
    private String value;

    @Column(name = "client_id", nullable = false)
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID clientId;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    public TokenJpaEntity() {
    }

    public TokenJpaEntity(UUID id, String value, UUID clientId, Instant createdAt) {
        this.id = id;
        this.value = value;
        this.clientId = clientId;
        this.createdAt = createdAt;
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
}
