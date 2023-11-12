package tech.reliab.course.morozovda.bank.entity;

import java.math.BigDecimal;

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

    public PaymentAccount(Client client, Bank bank, BigDecimal balance) {
        super(client, bank);
        this.balance = balance;
    }

    public PaymentAccount(int id, Client client, Bank bank, BigDecimal balance) {
        super(id, client, bank);
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "PaymentAccount:{" +
                "\n account='" + super.toString() + "'" +
                ",\n balance='" + String.format("%.2f", getBalance()) + "'" +
                "\n}";
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
