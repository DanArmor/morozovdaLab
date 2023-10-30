package tech.reliab.course.morozovda.bank.entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.UUID;

public class Bank {
    public static final BigDecimal MAX_RATING = new BigDecimal("100");
    public static final BigDecimal MAX_TOTAL_MONEY = new BigDecimal("1000000");
    public static final BigDecimal MAX_INTEREST_RATE = new BigDecimal("20");
    private UUID id;
    private String name;
    private int officeCount;
    private int atmCount;
    private int employeeCount;
    private int clientCount;
    private byte rating;
    private BigDecimal totalMoney;
    private BigDecimal interestRate;

    public Bank() {
        initWithDefaults();
    }

    public Bank(Bank bank) {
        this.id = UUID.fromString(bank.id.toString());
        this.name = bank.name;
        this.officeCount = bank.officeCount;
        this.atmCount = bank.atmCount;
        this.employeeCount = bank.employeeCount;
        this.clientCount = bank.clientCount;
        this.rating = bank.rating;
        this.totalMoney = bank.totalMoney;
        this.interestRate = bank.interestRate;
    }

    public Bank(String name) {
        initWithDefaults();
        this.name = name;
    }

    public Bank(UUID id, String name) {
        initWithDefaults();
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bank:{" +
                "\n id='" + getId() + "'" +
                ",\n name='" + getName() + "'" +
                ",\n officeCount='" + getOfficeCount() + "'" +
                ",\n atmCount='" + getAtmCount() + "'" +
                ",\n employeeCount='" + getEmployeeCount() + "'" +
                ",\n clientCount='" + getClientCount() + "'" +
                ",\n rating='" + getRating() + "'" +
                ",\n totalMoney='" + String.format("%.2f", getTotalMoney()) + "'" +
                ",\n interestRate='" + String.format("%.2f", getInterestRate()) + "'" +
                "\n}";
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOfficeCount() {
        return this.officeCount;
    }

    public void setOfficeCount(int officeCount) {
        this.officeCount = officeCount;
    }

    public int getAtmCount() {
        return this.atmCount;
    }

    public void setAtmCount(int atmCount) {
        this.atmCount = atmCount;
    }

    public int getEmployeeCount() {
        return this.employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public int getClientCount() {
        return this.clientCount;
    }

    public void setClientCount(int clientCount) {
        this.clientCount = clientCount;
    }

    public byte getRating() {
        return this.rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

    public BigDecimal getTotalMoney() {
        return this.totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    private void initWithDefaults() {
        id = UUID.randomUUID();
        name = "No name";
        officeCount = 0;
        atmCount = 0;
        employeeCount = 0;
        clientCount = 0;
        rating = 0;
        totalMoney = new BigDecimal("0");
        interestRate = new BigDecimal("0");
    }

}
