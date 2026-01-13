package bank_back.bank_back.persistence.dao.jpa;

import bank_back.bank_back.persistence.dao.jpa.entity.ClientJpaEntity;
import java.util.Optional;

public interface ClientJpaDao extends GenericJpaDao<ClientJpaEntity, Long> {
    Optional<ClientJpaEntity> findByLogin(String login);

    Optional<ClientJpaEntity> findByDNI(String DNI);
}
