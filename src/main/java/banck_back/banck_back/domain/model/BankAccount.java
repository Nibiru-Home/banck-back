package banck_back.banck_back.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BankAccount {

    private Long id;
    private BigDecimal balance;
    private String iban;
    private Client client;
    private List<BankMovement> movements = new ArrayList<>();
    private List<CreditCard> creditCards = new ArrayList<>();

    public BankAccount() {
    }

    public BankAccount(BigDecimal balance, String iban, Client client) {
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<BankMovement> getMovements() {
        return movements;
    }

    public void setMovements(List<BankMovement> movements) {
        this.movements = movements;
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BankAccount that = (BankAccount) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(balance, that.balance) &&
                Objects.equals(iban, that.iban) &&
                Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, iban, client);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", balance=" + balance +
                ", iban='" + iban + '\'' +
                ", client=" + (client != null ? client.getLogin() : "null") +
                '}';
    }
}