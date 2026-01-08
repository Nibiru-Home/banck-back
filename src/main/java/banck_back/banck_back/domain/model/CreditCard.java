package banck_back.banck_back.domain.model;
import java.util.Date;
import java.util.Objects;

public class CreditCard {

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return Objects.equals(cardNumber, that.cardNumber) && 
               Objects.equals(expiryDate, that.expiryDate) && 
               Objects.equals(cvc, that.cvc) && 
               Objects.equals(fullName, that.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, expiryDate, cvc, fullName);
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expiryDate=" + expiryDate +
                ", cvc='***'" +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}