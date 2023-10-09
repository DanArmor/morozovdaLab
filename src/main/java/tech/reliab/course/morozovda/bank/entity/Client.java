package tech.reliab.course.morozovda.bank.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Client extends Person {
    private String placeOfWork;
    private BigDecimal monthlyIncome;
    private Bank bank;
    private int creditRating;
    public static final double MAX_client_MONTHLY_INCOME = 10000;

    private void initWithDefaults() {
        placeOfWork = "No place of work";
        monthlyIncome = null;
        bank = null;
        creditRating = 0;
    }

    public Client() {
        initWithDefaults();
    }

    public Client(UUID id, String name, LocalDate birthDate, String placeOfWork, BigDecimal monthlyIncome, Bank bank,
            int creditRating) {
        this.id = id;
        this.name = name;
        this.birthdDate = birthDate;
        this.placeOfWork = placeOfWork;
        this.monthlyIncome = monthlyIncome;
        this.bank = bank;
        this.creditRating = creditRating;
    }

    @Override
    public String toString() {
        return "{" +
                " placeOfWork='" + getPlaceOfWork() + "'" +
                ", monthlyIncome='" + getMonthlyIncome() + "'" +
                ", bank='" + getBank() + "'" +
                ", creditRating='" + getCreditRating() + "'" +
                "}";
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

    public int getCreditRating() {
        return this.creditRating;
    }

    public void setCreditRating(int creditRating) {
        this.creditRating = creditRating;
    }

}