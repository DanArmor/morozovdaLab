package tech.reliab.course.morozovda.bank.entity;

import java.util.UUID;

public class Account {
    protected UUID id;
    protected Client client;
    protected Bank bank;

    public Account() {
        initWithDefaults();
    }

    public Account(Client client, Bank bank) {
        initWithDefaults();
        this.bank = bank;
    }

    public Account(UUID id, Client client, Bank bank) {
        this.id = id;
        this.client = client;
        this.bank = bank;
    }

    public Account(Account account) {
        this.id = UUID.fromString(account.id.toString());
        this.client = new Client(account.client);
        this.bank = new Bank(account.bank);
    }

    @Override
    public String toString() {
        return "{" +
                "\n id='" + getId() + "'" +
                ",\n client='" + getClient() + "'" +
                ",\n bank='" + getBank() + "'" +
                "\n}";
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Bank getBank() {
        return this.bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    private void initWithDefaults() {
        id = UUID.randomUUID();
        client = null;
        bank = null;
    }

}
