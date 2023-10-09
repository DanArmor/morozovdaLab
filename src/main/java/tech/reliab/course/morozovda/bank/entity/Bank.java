package tech.reliab.course.morozovda.bank.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class Bank {
    public static final BigDecimal MAX_BANK_RATING = new BigDecimal("100");
    public static final BigDecimal MAX_BANK_TOTAL_MONEY = new BigDecimal("1000000");
    public static final BigDecimal MAX_BANK_INTEREST_RATE = new BigDecimal("20");
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
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", officeCount='" + getOfficeCount() + "'" +
                ", atmCount='" + getAtmCount() + "'" +
                ", employeeCount='" + getEmployeeCount() + "'" +
                ", clientCount='" + getclientCount() + "'" +
                ", rating='" + getRating() + "'" +
                ", totalMoney='" + getTotalMoney() + "'" +
                ", interestRate='" + getInterestRate() + "'" +
                "}";
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

    public int getclientCount() {
        return this.clientCount;
    }

    public void setclientCount(int clientCount) {
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
        totalMoney = null;
        interestRate = null;
    }

}
