package banck_back.banck_back.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class BankMovement {

    private Long id;
    private MovementType movementType;
    private MovementOrigin movementOrigin;
    private CreditCard originCreditCard;
    private LocalDateTime timestamp;
    private BigDecimal amount;
    private String concept;
    private BankAccount bankAccount;

    public BankMovement() {
    }

    public BankMovement(MovementType movementType, MovementOrigin movementOrigin, CreditCard originCreditCard,
            LocalDateTime timestamp,
            BigDecimal amount, String concept, BankAccount bankAccount) {
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

    public CreditCard getOriginCreditCard() {
        return originCreditCard;
    }

    public void setOriginCreditCard(CreditCard originCreditCard) {
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

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BankMovement that = (BankMovement) o;
        return Objects.equals(id, that.id) &&
                movementType == that.movementType &&
                movementOrigin == that.movementOrigin &&
                Objects.equals(originCreditCard, that.originCreditCard) &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(concept, that.concept);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movementType, movementOrigin, originCreditCard, timestamp, amount, concept);
    }

    @Override
    public String toString() {
        return "BankMovement{" +
                "id=" + id +
                ", movementType=" + movementType +
                ", movementOrigin=" + movementOrigin +
                ", timestamp=" + timestamp +
                ", amount=" + amount +
                ", concept='" + concept + '\'' +
                '}';
    }
}