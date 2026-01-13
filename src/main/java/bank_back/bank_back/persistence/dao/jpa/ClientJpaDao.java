package bank_back.bank_back.persistence.dao.jpa;

import bank_back.bank_back.persistence.dao.jpa.entity.ClientJpaEntity;
import java.util.Optional;
import java.util.UUID;

public interface ClientJpaDao extends GenericJpaDao<ClientJpaEntity, UUID> {
    Optional<ClientJpaEntity> findByLogin(String login);

    Optional<ClientJpaEntity> findByDNI(String DNI);
}
