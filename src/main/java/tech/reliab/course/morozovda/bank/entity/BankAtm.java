package main.java.tech.reliab.course.morozovda.bank.entity;

import java.math.BigDecimal;
import java.util.UUID;

enum BankAtmStatus {
    NOT_WORKING
}

public class BankAtm {
    private UUID id;
    private String name;
    private String address;
    private BankAtmStatus status;
    private Bank bank;
    private BankOffice bankOffice;
    private Employee employee;
    private boolean isCashWithdrawalAvailable;
    private boolean isCashDepositAvailable;
    private BigDecimal totalMoney;
    private BigDecimal maintenanceCost;

    private void initWithDefaults() {
        id = UUID.randomUUID();
        name = "No name";
        address = "No address";
        status = BankAtmStatus.NOT_WORKING;
        bank = null;
        bankOffice = null;
        employee = null;
        isCashWithdrawalAvailable = false;
        isCashDepositAvailable = false;
        totalMoney = null;
        maintenanceCost = null;
    }

    public BankAtm() {
        initWithDefaults();
    }

    public BankAtm(String name, String address) {
        initWithDefaults();
        this.name = name;
        this.address = address;
    }

    public BankAtm(UUID id, String name, String address, BankAtmStatus status, Bank bank, BankOffice bankOffice,
            Employee employee, boolean isCashWithdrawalAvailable, boolean isCashDepositAvailable, BigDecimal totalMoney,
            BigDecimal maintenanceCost) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.status = status;
        this.bank = bank;
        this.bankOffice = bankOffice;
        this.employee = employee;
        this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
        this.isCashDepositAvailable = isCashDepositAvailable;
        this.totalMoney = totalMoney;
        this.maintenanceCost = maintenanceCost;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", address='" + getAddress() + "'" +
                ", status='" + getStatus() + "'" +
                ", bank='" + getBank() + "'" +
                ", bankOffice='" + getBankOffice() + "'" +
                ", employee='" + getEmployee() + "'" +
                ", isCashWithdrawalAvailable='" + isIsCashWithdrawalAvailable() + "'" +
                ", isCashDepositAvailable='" + isIsCashDepositAvailable() + "'" +
                ", totalMoney='" + getTotalMoney() + "'" +
                ", maintenanceCost='" + getMaintenanceCost() + "'" +
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

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BankAtmStatus getStatus() {
        return this.status;
    }

    public void setStatus(BankAtmStatus status) {
        this.status = status;
    }

    public Bank getBank() {
        return this.bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public BankOffice getBankOffice() {
        return this.bankOffice;
    }

    public void setBankOffice(BankOffice bankOffice) {
        this.bankOffice = bankOffice;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public boolean isIsCashWithdrawalAvailable() {
        return this.isCashWithdrawalAvailable;
    }

    public boolean getIsCashWithdrawalAvailable() {
        return this.isCashWithdrawalAvailable;
    }

    public void setIsCashWithdrawalAvailable(boolean isCashWithdrawalAvailable) {
        this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
    }

    public boolean isIsCashDepositAvailable() {
        return this.isCashDepositAvailable;
    }

    public boolean getIsCashDepositAvailable() {
        return this.isCashDepositAvailable;
    }

    public void setIsCashDepositAvailable(boolean isCashDepositAvailable) {
        this.isCashDepositAvailable = isCashDepositAvailable;
    }

    public BigDecimal getTotalMoney() {
        return this.totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getMaintenanceCost() {
        return this.maintenanceCost;
    }

    public void setMaintenanceCost(BigDecimal maintenanceCost) {
        this.maintenanceCost = maintenanceCost;
    }

}