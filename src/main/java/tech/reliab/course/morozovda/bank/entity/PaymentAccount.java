package main.java.tech.reliab.course.morozovda.bank.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentAccount extends Account {
    private BigDecimal balance;

    private void initWithDefaults() {
        balance = new BigDecimal("0");
    }

    public PaymentAccount() {
        super();
        initWithDefaults();
    }

    public PaymentAccount(UUID id, User user, Bank bank, BigDecimal balance) {
        super(id, user, bank);
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "{" +
                " balance='" + getBalance() + "'" +
                "}";
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
