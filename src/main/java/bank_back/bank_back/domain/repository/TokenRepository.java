package bank_back.bank_back.domain.repository;

import java.util.Optional;
import bank_back.bank_back.domain.model.Token;

public interface TokenRepository {

    void save(Token token);
    Optional<Token> findByValue(String value);
}