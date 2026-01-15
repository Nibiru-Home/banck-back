package bank_back.bank_back.persistence.dao.jpa;

import java.util.Optional;
import java.util.UUID;

import bank_back.bank_back.persistence.dao.jpa.entity.TokenJpaEntity;

public interface TokenJpaDao extends GenericJpaDao<TokenJpaEntity, UUID> {

    Optional<TokenJpaEntity> findByValue(String value);

    Optional<TokenJpaEntity> findByClientId(UUID clientId);
}
