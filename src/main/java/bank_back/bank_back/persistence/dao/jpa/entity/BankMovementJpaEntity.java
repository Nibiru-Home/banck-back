package bank_back.bank_back.persistence.dao.jpa.entity;

import bank_back.bank_back.domain.model.MovementOrigin;
import bank_back.bank_back.domain.model.MovementType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bank_movements")
public class BankMovementJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MovementType movementType;

    @Enumerated(EnumType.STRING)
    private MovementOrigin movementOrigin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_credit_card_id")
    private CreditCardJpaEntity originCreditCard;

    private LocalDateTime timestamp;
    private BigDecimal amount;
    private String concept;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_account_id")
    private BankAccountJpaEntity bankAccount;

    public BankMovementJpaEntity() {
    }

    public BankMovementJpaEntity(Long id, MovementType movementType, MovementOrigin movementOrigin, CreditCardJpaEntity originCreditCard, LocalDateTime timestamp, BigDecimal amount, String concept, BankAccountJpaEntity bankAccount) {
        this.id = id;
        this.movementType = movementType;
        this.movementOrigin = movementOrigin;
        this.originCreditCard = originCreditCard;
        this.timestamp = timestamp;
        this.amount = amount;
        this.concept = concept;
        this.bankAccount = bankAccount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }

    public MovementOrigin getMovementOrigin() {
        return movementOrigin;
    }

    public void setMovementOrigin(MovementOrigin movementOrigin) {
        this.movementOrigin = movementOrigin;
    }

    public CreditCardJpaEntity getOriginCreditCard() {
        return originCreditCard;
    }

    public void setOriginCreditCard(CreditCardJpaEntity originCreditCard) {
        this.originCreditCard = originCreditCard;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public BankAccountJpaEntity getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccountJpaEntity bankAccount) {
        this.bankAccount = bankAccount;
    }
}
