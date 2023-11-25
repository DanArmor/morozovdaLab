package tech.reliab.course.morozovda.bank.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private static int currentId;
    public static final BigDecimal MAX_RATING = new BigDecimal("100");
    public static final BigDecimal MAX_TOTAL_MONEY = new BigDecimal("1000000");
    public static final BigDecimal MAX_INTEREST_RATE = new BigDecimal("20");
    private int id;
    private String name;
    private int officeCount;
    private int atmCount;
    private int employeeCount;
    private int clientCount;
    private byte rating;
    private BigDecimal totalMoney;
    private BigDecimal interestRate;
    private List<Employee> employees;
    private List<Client> clients;
    private List<BankOffice> bankOffices;
    private List<BankAtm> bankAtms;
    private List<Account> accounts;

    public Bank() {
        initId();
        initWithDefaults();
    }

    private void initId() {
        id = currentId++;
    }

    public Bank(Bank bank) {
        this.id = bank.id;
        this.name = bank.name;
        this.officeCount = bank.officeCount;
        this.atmCount = bank.atmCount;
        this.employeeCount = bank.employeeCount;
        this.clientCount = bank.clientCount;
        this.rating = bank.rating;
        this.totalMoney = bank.totalMoney;
        this.interestRate = bank.interestRate;
        employees = bank.employees;
        clients = bank.clients;
        bankOffices = bank.bankOffices;
        bankAtms = bank.bankAtms;
        accounts = bank.accounts;
    }

    public Bank(String name) {
        initId();
        initWithDefaults();
        this.name = name;
    }

    public Bank(int id, String name) {
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

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void removeClient(Client client) {
        clients.remove(clients.indexOf(client));
    }

    public void addOffice(BankOffice bankOffice) {
        for(BankAtm bankAtm : bankOffice.getAtms()) {
            addAtm(bankAtm);
        }
        for(Employee employee : bankOffice.getEmployees()) {
            addEmployee(employee);
        }
        bankOffices.add(bankOffice);
    }

    public void addAtm(BankAtm bankAtm) {
        bankAtms.add(bankAtm);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    private void initWithDefaults() {
        name = "No name";
        officeCount = 0;
        atmCount = 0;
        employeeCount = 0;
        clientCount = 0;
        rating = 0;
        totalMoney = new BigDecimal("0");
        interestRate = new BigDecimal("0");
        employees = new ArrayList<>();
        clients = new ArrayList<>();
        bankOffices = new ArrayList<>();
        bankAtms = new ArrayList<>();
        accounts = new ArrayList<>();
    }

}
