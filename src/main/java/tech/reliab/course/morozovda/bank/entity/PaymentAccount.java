package tech.reliab.course.morozovda.bank.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentAccount extends Account {
    private BigDecimal balance;

    public PaymentAccount() {
        super();
        initWithDefaults();
    }

    public PaymentAccount(PaymentAccount paymentAccount) {
        super(paymentAccount.id, paymentAccount.client, paymentAccount.bank);
        this.balance = paymentAccount.balance;
    }

    public PaymentAccount(UUID id, Client client, Bank bank, BigDecimal balance) {
        super(id, client, bank);
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

    private void initWithDefaults() {
        balance = new BigDecimal("0");
    }

}
