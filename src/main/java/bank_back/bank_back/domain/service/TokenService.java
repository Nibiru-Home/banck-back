package bank_back.bank_back.domain.service;

import java.util.UUID;
import bank_back.bank_back.domain.model.Token;

public interface TokenService {

    Token generate(UUID clientId);

    boolean validate(String tokenValue);

    UUID extractClientId(String tokenValue);
}
