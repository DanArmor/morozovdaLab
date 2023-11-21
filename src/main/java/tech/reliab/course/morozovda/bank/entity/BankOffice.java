package tech.reliab.course.morozovda.bank.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankOffice {
    private static int currentId;
    private int id;
    private String name;
    private String address;
    private Bank bank;
    private boolean isWorking;
    private boolean isAtmPlaceable;
    private int atmCount;
    private boolean isCreditAvailable;
    private boolean isCashWithdrawalAvailable;
    private boolean isCashDepositAvailable;
    private BigDecimal totalMoney;
    private BigDecimal rentPrice;
    private List<Employee> employees;
    private List<BankAtm> bankAtms;

    private void initId() {
        id = currentId++;
    }

    public BankOffice(String name, String address) {
        initId();
        initWithDefaults();
        this.name = name;
        this.address = address;
    }

    public BankOffice(int id, String name, String address, Bank bank, boolean isWorking, boolean isAtmPlaceable,
            int atmCount, boolean isCreditAvailable, boolean isCashWithdrawalAvailable, boolean isCashDepositAvailable,
            BigDecimal totalMoney, BigDecimal rentPrice) {
        initWithDefaults();
        this.id = id;
        this.name = name;
        this.address = address;
        this.bank = bank;
        this.isWorking = isWorking;
        this.isAtmPlaceable = isAtmPlaceable;
        this.atmCount = atmCount;
        this.isCreditAvailable = isCreditAvailable;
        this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
        this.isCashDepositAvailable = isCashDepositAvailable;
        this.totalMoney = totalMoney;
        this.rentPrice = rentPrice;
    }

    public BankOffice(String name, String address, Bank bank, boolean isWorking, boolean isAtmPlaceable,
            int atmCount, boolean isCreditAvailable, boolean isCashWithdrawalAvailable, boolean isCashDepositAvailable,
            BigDecimal totalMoney, BigDecimal rentPrice) {
        initId();
        initWithDefaults();
        this.name = name;
        this.address = address;
        this.bank = bank;
        this.isWorking = isWorking;
        this.isAtmPlaceable = isAtmPlaceable;
        this.atmCount = atmCount;
        this.isCreditAvailable = isCreditAvailable;
        this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
        this.isCashDepositAvailable = isCashDepositAvailable;
        this.totalMoney = totalMoney;
        this.rentPrice = rentPrice;
    }

    public BankOffice(BankOffice bankOffice) {
        this.id = bankOffice.id;
        this.name = bankOffice.name;
        this.address = bankOffice.address;
        this.bank = new Bank(bankOffice.bank);
        this.isWorking = bankOffice.isWorking;
        this.isAtmPlaceable = bankOffice.isAtmPlaceable;
        this.atmCount = bankOffice.atmCount;
        this.isCreditAvailable = bankOffice.isCreditAvailable;
        this.isCashWithdrawalAvailable = bankOffice.isCashWithdrawalAvailable;
        this.isCashDepositAvailable = bankOffice.isCashDepositAvailable;
        this.totalMoney = bankOffice.totalMoney;
        this.rentPrice = bankOffice.rentPrice;
        employees = bankOffice.employees;
        bankAtms = bankOffice.bankAtms;
    }

    @Override
    public String toString() {
        return "BankOffice:{" +
                "\n id='" + getId() + "'" +
                ",\n name='" + getName() + "'" +
                ",\n address='" + getAddress() + "'" +
                ",\n bank='" + getBank().getName() + "'" +
                ",\n isWorking='" + isIsWorking() + "'" +
                ",\n isAtmPlaceable='" + isIsAtmPlaceable() + "'" +
                ",\n atmCount='" + getAtmCount() + "'" +
                ",\n isCreditAvailable='" + isIsCreditAvailable() + "'" +
                ",\n isCashWithdrawalAvailable='" + isIsCashWithdrawalAvailable() + "'" +
                ",\n isCashDepositAvailable='" + isIsCashDepositAvailable() + "'" +
                ",\n totalMoney='" + String.format("%.2f", getTotalMoney()) + "'" +
                ",\n rentPrice='" + String.format("%.2f", getRentPrice()) + "'" +
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

    public Bank getBank() {
        return this.bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public boolean isIsWorking() {
        return this.isWorking;
    }

    public boolean getIsWorking() {
        return this.isWorking;
    }

    public void setIsWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }

    public boolean isIsAtmPlaceable() {
        return this.isAtmPlaceable;
    }

    public boolean getIsAtmPlaceable() {
        return this.isAtmPlaceable;
    }

    public void setIsAtmPlaceable(boolean isAtmPlaceable) {
        this.isAtmPlaceable = isAtmPlaceable;
    }

    public int getAtmCount() {
        return this.atmCount;
    }

    public void setAtmCount(int atmCount) {
        this.atmCount = atmCount;
    }

    public boolean isIsCreditAvailable() {
        return this.isCreditAvailable;
    }

    public boolean getIsCreditAvailable() {
        return this.isCreditAvailable;
    }

    public void setIsCreditAvailable(boolean isCreditAvailable) {
        this.isCreditAvailable = isCreditAvailable;
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

    public BigDecimal getRentPrice() {
        return this.rentPrice;
    }

    public void setRentPrice(BigDecimal rentPrice) {
        this.rentPrice = rentPrice;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addAtm(BankAtm bankAtm) {
        bankAtms.add(bankAtm);
    }

    public List<BankAtm> getAtms() {
        return bankAtms;
    }

    private void initWithDefaults() {
        name = "No name";
        address = "No address";
        bank = null;
        isWorking = false;
        isAtmPlaceable = false;
        atmCount = 0;
        isCreditAvailable = false;
        isCashWithdrawalAvailable = false;
        isCashDepositAvailable = false;
        totalMoney = new BigDecimal("0");
        rentPrice = new BigDecimal("0");
        employees = new ArrayList<>();
        bankAtms = new ArrayList<>();
    }

}