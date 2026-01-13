package bank_back.bank_back.persistence.dao.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bank_accounts")
public class BankAccountJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal balance;
    private String iban;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientJpaEntity client;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BankMovementJpaEntity> movements = new ArrayList<>();

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CreditCardJpaEntity> creditCards = new ArrayList<>();

    public BankAccountJpaEntity() {
    }

    public BankAccountJpaEntity(Long id, BigDecimal balance, String iban, ClientJpaEntity client) {
        this.id = id;
        this.balance = balance;
        this.iban = iban;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public ClientJpaEntity getClient() {
        return client;
    }

    public void setClient(ClientJpaEntity client) {
        this.client = client;
    }

    public List<BankMovementJpaEntity> getMovements() {
        return movements;
    }

    public void setMovements(List<BankMovementJpaEntity> movements) {
        this.movements = movements;
    }

    public List<CreditCardJpaEntity> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCardJpaEntity> creditCards) {
        this.creditCards = creditCards;
    }
}
