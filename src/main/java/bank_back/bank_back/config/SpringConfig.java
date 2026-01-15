package bank_back.bank_back.config;

import bank_back.bank_back.domain.repository.BankAccountRepository;
import bank_back.bank_back.domain.repository.BankMovementRepository;
import bank_back.bank_back.domain.repository.ClientRepository;
import bank_back.bank_back.domain.repository.CreditCardRepository;
import bank_back.bank_back.domain.service.BankAccountService;
import bank_back.bank_back.domain.service.BankMovementService;
import bank_back.bank_back.domain.service.ClientService;
import bank_back.bank_back.domain.service.CreditCardService;
import bank_back.bank_back.domain.service.impl.BankAccountServiceImpl;
import bank_back.bank_back.domain.service.impl.BankMovementServiceImpl;
import bank_back.bank_back.domain.service.impl.ClientServiceImpl;
import bank_back.bank_back.domain.service.impl.CreditCardServiceImpl;
import bank_back.bank_back.persistence.dao.jpa.BankAccountJpaDao;
import bank_back.bank_back.persistence.dao.jpa.BankMovementJpaDao;
import bank_back.bank_back.persistence.dao.jpa.ClientJpaDao;
import bank_back.bank_back.persistence.dao.jpa.CreditCardJpaDao;
import bank_back.bank_back.persistence.dao.jpa.impl.BankAccountJpaDaoImpl;
import bank_back.bank_back.persistence.dao.jpa.impl.BankMovementJpaDaoImpl;
import bank_back.bank_back.persistence.dao.jpa.impl.ClientJpaDaoImpl;
import bank_back.bank_back.persistence.dao.jpa.impl.CreditCardJpaDaoImpl;
import bank_back.bank_back.persistence.repository.impl.BankAccountRepositoryImpl;
import bank_back.bank_back.persistence.repository.impl.BankMovementRepositoryImpl;
import bank_back.bank_back.persistence.repository.impl.ClientRepositoryImpl;
import bank_back.bank_back.persistence.repository.impl.CreditCardRepositoryImpl;
import bank_back.bank_back.domain.repository.TokenRepository;
import bank_back.bank_back.domain.service.TokenService;
import bank_back.bank_back.domain.service.impl.TokenServiceImpl;
import bank_back.bank_back.persistence.dao.jpa.TokenJpaDao;
import bank_back.bank_back.persistence.dao.jpa.impl.TokenJpaDaoImpl;
import bank_back.bank_back.persistence.repository.impl.TokenRepositoryImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "bank_back.bank_back.persistence.dao.jpa")
@EntityScan(basePackages = "bank_back.bank_back.persistence.dao.jpa.entity")
@org.springframework.context.annotation.ComponentScan(basePackages = "bank_back.bank_back")
public class SpringConfig {

    // JPA DAOs
    @Bean
    public BankAccountJpaDao bankAccountJpaDao() {
        return new BankAccountJpaDaoImpl();
    }

    @Bean
    public BankMovementJpaDao bankMovementJpaDao() {
        return new BankMovementJpaDaoImpl();
    }

    @Bean
    public ClientJpaDao clientJpaDao() {
        return new ClientJpaDaoImpl();
    }

    @Bean
    public CreditCardJpaDao creditCardJpaDao() {
        return new CreditCardJpaDaoImpl();
    }

    // Repositories
    @Bean
    public BankAccountRepository bankAccountRepository(
            BankAccountJpaDao bankAccountJpaDao,
            BankMovementJpaDao bankMovementJpaDao,
            CreditCardJpaDao creditCardJpaDao) {
        return new BankAccountRepositoryImpl(bankAccountJpaDao, bankMovementJpaDao, creditCardJpaDao);
    }

    @Bean
    public BankMovementRepository bankMovementRepository(BankMovementJpaDao bankMovementJpaDao) {
        return new BankMovementRepositoryImpl(bankMovementJpaDao);
    }

    @Bean
    public ClientRepository clientRepository(ClientJpaDao clientJpaDao, TokenJpaDao tokenJpaDao) {
        return new ClientRepositoryImpl(clientJpaDao, tokenJpaDao);
    }

    @Bean
    public CreditCardRepository creditCardRepository(CreditCardJpaDao creditCardJpaDao) {
        return new CreditCardRepositoryImpl(creditCardJpaDao);
    }

    @Bean
    public TokenRepository tokenRepository(TokenJpaDao tokenJpaDao) {
        return new TokenRepositoryImpl(tokenJpaDao);
    }

    @Bean
    public TokenJpaDao tokenJpaDao() {
        return new TokenJpaDaoImpl();
    }

    // Services
    @Bean
    public BankAccountService bankAccountService(BankAccountRepository bankAccountRepository) {
        return new BankAccountServiceImpl(bankAccountRepository);
    }

    @Bean
    public BankMovementService bankMovementService(BankMovementRepository bankMovementRepository) {
        return new BankMovementServiceImpl(bankMovementRepository);
    }

    @Bean
    public ClientService clientService(ClientRepository clientRepository) {
        return new ClientServiceImpl(clientRepository);
    }

    @Bean
    public CreditCardService creditCardService(CreditCardRepository creditCardRepository) {
        return new CreditCardServiceImpl(creditCardRepository);
    }

    @Bean
    public TokenService tokenService(TokenRepository tokenRepository) {
        return new TokenServiceImpl(tokenRepository);
    }

    @Bean
    public bank_back.bank_back.domain.service.PagoTarjeta pagoTarjeta(
            bank_back.bank_back.domain.service.ClientService clientService,
            bank_back.bank_back.domain.service.BankAccountService bankAccountService,
            bank_back.bank_back.domain.service.CreditCardService creditCardService) {
        return new bank_back.bank_back.domain.service.impl.PagoTarjetaImpl(clientService, bankAccountService,
                creditCardService);
    }
}
