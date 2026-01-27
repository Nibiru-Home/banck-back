package bank_back.bank_back.domain.service.impl;

import bank_back.bank_back.domain.service.TokenService;
import bank_back.bank_back.domain.model.Token;
import bank_back.bank_back.domain.repository.TokenRepository;
import java.time.Instant;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Token generate(UUID clientId) {

        String value = UUID.randomUUID().toString();
        Instant now = Instant.now();

        Token token = new Token(
                UUID.randomUUID(),
                value,
                clientId,
                now.plusSeconds(3600));

        tokenRepository.save(token);

        return token;
    }

    @Override
    public boolean validate(String tokenValue) {
        return tokenRepository.findByValue(tokenValue)
                .filter(t -> !t.isExpired())
                .isPresent();
    }

    @Override
    public UUID extractClientId(String tokenValue) {
        return tokenRepository.findByValue(tokenValue)
                .map(Token::getClientId)
                .orElseThrow(() -> new RuntimeException("Token inválido"));
    }
}
