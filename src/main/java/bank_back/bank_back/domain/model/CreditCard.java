package bank_back.bank_back.domain.model;

import java.util.Date;
import java.util.Objects;

public class CreditCard {

    private Long id;
    private String cardNumber;
    private Date expiryDate;
    private String cvc;
    private String fullName;
    private BankAccount bankAccount;

    public CreditCard() {
    }

    public CreditCard(String cardNumber, Date expiryDate, String cvc, String fullName, BankAccount bankAccount) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvc = cvc;
        this.fullName = fullName;
        this.bankAccount = bankAccount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
        CreditCard that = (CreditCard) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(cardNumber, that.cardNumber) &&
                Objects.equals(expiryDate, that.expiryDate) &&
                Objects.equals(cvc, that.cvc) &&
                Objects.equals(fullName, that.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardNumber, expiryDate, cvc, fullName);
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", expiryDate=" + expiryDate +
                ", cvc='***'" +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}