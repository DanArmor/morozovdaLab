package main.java.tech.reliab.course.morozovda.bank.entity;

import java.util.UUID;

public class Account {
    protected UUID id;
    protected User user;
    protected Bank bank;

    private void initWithDefaults() {
        id = UUID.randomUUID();
        user = null;
        bank = null;
    }

    public Account() {
        initWithDefaults();
    }

    public Account(User user, Bank bank) {
        initWithDefaults();
        this.bank = bank;
    }

    public Account(UUID id, User user, Bank bank) {
        this.id = id;
        this.user = user;
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", user='" + getUser() + "'" +
                ", bank='" + getBank() + "'" +
                "}";
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bank getBank() {
        return this.bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

}
