package tech.reliab.course.morozovda.bank.entity;

import com.google.gson.annotations.Expose;

public class Account {
    private static int currentId;
    @Expose(serialize = true)
    protected int id;
    @Expose(serialize = false)
    protected Client client;
    @Expose(serialize = false)
    protected Bank bank;

    private void initId() {
        id = currentId++;
    }

    public Account() {
        initId();
        initWithDefaults();
    }

    public Account(Client client, Bank bank) {
        initId();
        initWithDefaults();
        this.client = client;
        this.bank = bank;
    }

    public Account(int id, Client client, Bank bank) {
        this.id = id;
        this.client = client;
        this.bank = bank;
    }

    public Account(Account account) {
        this.id = account.id;
        this.client = new Client(account.client);
        this.bank = new Bank(account.bank);
    }

    @Override
    public String toString() {
        return "Account:{" +
                "\n id='" + getId() + "'" +
                ",\n client='" + getClient() + "'" +
                ",\n bank='" + getBank().getName() + "'" +
                "\n}";
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
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
        client = null;
        bank = null;
    }

}
