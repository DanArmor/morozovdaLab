package main.java.tech.reliab.course.morozovda.bank.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class Bank {
    private UUID id;
    private String name;
    private int officeCount;
    private int atmCount;
    private int employeeCount;
    private int userCount;
    private byte rating;
    private BigDecimal totalMoney;
    private double interestRate;

    private void initWithDefaults() {
        id = UUID.randomUUID();
        name = "No name";
        officeCount = 0;
        atmCount = 0;
        employeeCount = 0;
        userCount = 0;
        rating = 0;
        totalMoney = null;
        interestRate = 0;
    }

    public Bank() {
        initWithDefaults();
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
                ", clientCount='" + getUserCount() + "'" +
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

    public int getUserCount() {
        return this.userCount;
    }

    public void setUserCount(int clientCount) {
        this.userCount = clientCount;
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

    public double getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

}
