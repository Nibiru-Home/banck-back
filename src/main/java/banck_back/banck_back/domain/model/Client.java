package banck_back.banck_back.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client {

    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String secondLastName;
    private String DNI;
    private String apiToken;
    private List<BankAccount> bankAccounts = new ArrayList<>();

    public Client() {
    }

    public Client(String login, String password, String firstName, String lastName, String secondLastName,
            String DNI, String apiToken) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.DNI = DNI;
        this.apiToken = apiToken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    @Override

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) &&
                Objects.equals(login, client.login) &&
                Objects.equals(password, client.password) &&
                Objects.equals(firstName, client.firstName) &&
                Objects.equals(lastName, client.lastName) &&
                Objects.equals(secondLastName, client.secondLastName) &&
                Objects.equals(DNI, client.DNI) &&
                Objects.equals(apiToken, client.apiToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, firstName, lastName, secondLastName, DNI, apiToken);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", secondLastName='" + secondLastName + '\'' +
                ", DNI='" + DNI + '\'' +
                ", apiToken='" + apiToken + '\'' +
                '}';
    }
}