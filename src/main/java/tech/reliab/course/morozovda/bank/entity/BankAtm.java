package tech.reliab.course.morozovda.bank.entity;

import java.math.BigDecimal;

public class BankAtm {
    public enum Status {
        NOT_WORKING,
        WORKING,
        NO_MONEY
    }

    private static int currentId;
    private int id;
    private String name;
    private String address;
    private Status status;
    private Bank bank;
    private BankOffice bankOffice;
    private boolean isCashWithdrawalAvailable;
    private boolean isCashDepositAvailable;
    private BigDecimal totalMoney;
    private BigDecimal maintenanceCost;

    private void initId() {
        id = currentId++;
    }

    public BankAtm(BankAtm bankAtm) {
        this.id = bankAtm.id;
        this.name = bankAtm.name;
        this.address = bankAtm.address;
        this.status = bankAtm.status;
        this.bank = new Bank(bankAtm.bank);
        this.bankOffice = new BankOffice(bankAtm.bankOffice);
        this.isCashWithdrawalAvailable = bankAtm.isCashWithdrawalAvailable;
        this.isCashDepositAvailable = bankAtm.isCashDepositAvailable;
        this.totalMoney = bankAtm.totalMoney;
        this.maintenanceCost = bankAtm.maintenanceCost;
    }

    public BankAtm() {
        initId();
        initWithDefaults();
    }

    public BankAtm(String name, String address) {
        initId();
        initWithDefaults();
        this.name = name;
        this.address = address;
    }

    public BankAtm(String name, String address, Status status, Bank bank, BankOffice bankOffice,
            Employee employee, boolean isCashWithdrawalAvailable, boolean isCashDepositAvailable, BigDecimal totalMoney,
            BigDecimal maintenanceCost) {
        initId();
        initWithDefaults();
        this.name = name;
        this.address = address;
        this.status = status;
        this.bank = bank;
        this.bankOffice = bankOffice;
        this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
        this.isCashDepositAvailable = isCashDepositAvailable;
        this.totalMoney = totalMoney;
        this.maintenanceCost = maintenanceCost;
    }

    public BankAtm(int id, String name, String address, Status status, Bank bank, BankOffice bankOffice,
            Employee employee, boolean isCashWithdrawalAvailable, boolean isCashDepositAvailable, BigDecimal totalMoney,
            BigDecimal maintenanceCost) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.status = status;
        this.bank = bank;
        this.bankOffice = bankOffice;
        this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
        this.isCashDepositAvailable = isCashDepositAvailable;
        this.totalMoney = totalMoney;
        this.maintenanceCost = maintenanceCost;
    }

    @Override
    public String toString() {
        return "BankAtm:{" +
                "\n id='" + getId() + "'" +
                ",\n name='" + getName() + "'" +
                ",\n address='" + getAddress() + "'" +
                ",\n status='" + getStatus() + "'" +
                ",\n bank='" + getBank().getName() + "'" +
                ",\n bankOffice=" + getBankOffice() +
                ",\n isCashWithdrawalAvailable='" + isIsCashWithdrawalAvailable() + "'" +
                ",\n isCashDepositAvailable='" + isIsCashDepositAvailable() + "'" +
                ",\n totalMoney='" + String.format("%.2f", getTotalMoney()) + "'" +
                ",\n maintenanceCost='" + String.format("%.2f", getMaintenanceCost()) + "'" +
                "\n}";
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
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

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
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

    private void initWithDefaults() {
        name = "No name";
        address = "No address";
        status = Status.NOT_WORKING;
        bank = null;
        bankOffice = null;
        isCashWithdrawalAvailable = false;
        isCashDepositAvailable = false;
        totalMoney = new BigDecimal("0");
        maintenanceCost = new BigDecimal("0");
    }

}
