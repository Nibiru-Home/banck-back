package banck_back.banck_back.domain.model;

import java.time.LocalDate;
import java.util.Objects;

public class CreditCard {

    private Long id;
    private String number;
    private LocalDate expirationDate;
    private int cvv;
    private String name;
    private BankAccount bankAccount;

    public CreditCard() {
    }

    public CreditCard(String number, LocalDate expirationDate, int cvv, String name, BankAccount bankAccount) {
        this.number = number;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.name = name;
        this.bankAccount = bankAccount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return cvv == that.cvv &&
                Objects.equals(id, that.id) &&
                Objects.equals(number, that.number) &&
                Objects.equals(expirationDate, that.expirationDate) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, expirationDate, cvv, name);
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", expirationDate=" + expirationDate +
                ", cvv=" + cvv +
                ", name='" + name + '\'' +
                '}';
    }
}