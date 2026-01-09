package bank_back.bank_back.domain.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class BankMovement {

    private Long id;
    private MovementType type;
    private MovementOrigin origin;
    private CreditCard originCreditCard;
    private Date date;
    private BigDecimal amount;
    private String concept;
    private BankAccount bankAccount;

    public BankMovement() {
    }

    public BankMovement(MovementType type, MovementOrigin origin, CreditCard originCreditCard, Date date,
            BigDecimal amount, String concept, BankAccount bankAccount) {
        this.type = type;
        this.origin = origin;
        this.originCreditCard = originCreditCard;
        this.date = date;
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

    public MovementType getType() {
        return type;
    }

    public void setType(MovementType type) {
        this.type = type;
    }

    public MovementOrigin getOrigin() {
        return origin;
    }

    public void setOrigin(MovementOrigin origin) {
        this.origin = origin;
    }

    public CreditCard getOriginCreditCard() {
        return originCreditCard;
    }

    public void setOriginCreditCard(CreditCard originCreditCard) {
        this.originCreditCard = originCreditCard;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
                type == that.type &&
                origin == that.origin &&
                Objects.equals(originCreditCard, that.originCreditCard) &&
                Objects.equals(date, that.date) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(concept, that.concept);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, origin, originCreditCard, date, amount, concept);
    }

    @Override
    public String toString() {
        return "BankMovement{" +
                "id=" + id +
                ", type=" + type +
                ", origin=" + origin +
                ", date=" + date +
                ", amount=" + amount +
                ", concept='" + concept + '\'' +
                '}';
    }
}