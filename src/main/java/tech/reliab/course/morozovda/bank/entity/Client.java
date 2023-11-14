package tech.reliab.course.morozovda.bank.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Client extends Person {
    public static final BigDecimal MAX_MONTHLY_INCOME = new BigDecimal("10000");
    private String placeOfWork;
    private BigDecimal monthlyIncome;
    private Bank bank;
    private BigDecimal creditRating;

    public Client() {
        initWithDefaults();
    }

    public Client(Client client) {
        super(client.id, client.name, client.birthdDate);
        this.placeOfWork = client.placeOfWork;
        this.monthlyIncome = client.monthlyIncome;
        this.bank = new Bank(client.bank);
        this.creditRating = client.creditRating;
    }

    public Client(String name, LocalDate birthDate, String placeOfWork, BigDecimal monthlyIncome, Bank bank,
            BigDecimal creditRating) {
        super(name, birthDate);
        initWithDefaults();
        this.name = name;
        this.birthdDate = birthDate;
        this.placeOfWork = placeOfWork;
        this.monthlyIncome = monthlyIncome;
        this.bank = bank;
        this.creditRating = creditRating;
    }

    public Client(int id, String name, LocalDate birthDate, String placeOfWork, BigDecimal monthlyIncome, Bank bank,
            BigDecimal creditRating) {
        super(id, name, birthDate);
        this.placeOfWork = placeOfWork;
        this.monthlyIncome = monthlyIncome;
        this.bank = bank;
        this.creditRating = creditRating;
    }

    @Override
    public String toString() {
        return "Client:{" +
                "\n person='" + super.toString() + "'" +
                ",\n placeOfWork='" + getPlaceOfWork() + "'" +
                ",\n monthlyIncome='" + String.format("%.2f", getMonthlyIncome()) + "'" +
                ",\n bank='" + getBank().getName() + "'" +
                ",\n creditRating='" + String.format("%.2f", getCreditRating()) + "'" +
                "\n}";
    }

    public String getPlaceOfWork() {
        return this.placeOfWork;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public BigDecimal getMonthlyIncome() {
        return this.monthlyIncome;
    }

    public void setMonthlyIncome(BigDecimal monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public Bank getBank() {
        return this.bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public BigDecimal getCreditRating() {
        return this.creditRating;
    }

    public void setCreditRating(BigDecimal creditRating) {
        this.creditRating = creditRating;
    }

    private void initWithDefaults() {
        placeOfWork = "No place of work";
        monthlyIncome = new BigDecimal("0");
        bank = null;
        creditRating = new BigDecimal("0");
    }

}