package tech.reliab.course.morozovda.bank.entity;

import java.time.LocalDate;
import java.math.BigDecimal;

public class Employee extends Person {
    private EmployeeJob job;
    private Bank bank;
    private boolean isWorkingFromHome;
    private BankOffice bankOffice;
    private boolean isCreditAvailable;
    private BigDecimal salary;

    public Employee() {
        super();
        initWithDefaults();
    }

    public Employee(Employee employee) {
        super(employee.id, employee.name, employee.birthdDate);
        this.job = employee.job;
        this.bank = new Bank(employee.bank);
        this.isWorkingFromHome = employee.isWorkingFromHome;
        this.bankOffice = new BankOffice(employee.bankOffice);
        this.isCreditAvailable = employee.isCreditAvailable;
        this.salary = new BigDecimal(employee.salary.toString());
    }

    public Employee(String name, LocalDate birthDate, EmployeeJob job, Bank bank, boolean isWorkingFromHome,
            BankOffice bankOffice, boolean isCreditAvailable, BigDecimal salary) {
        super(name, birthDate);
        this.job = job;
        this.bank = bank;
        this.isWorkingFromHome = isWorkingFromHome;
        this.bankOffice = bankOffice;
        this.isCreditAvailable = isCreditAvailable;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "{" +
                " job='" + getJob() + "'" +
                ", bank='" + getBank() + "'" +
                ", isWorkingFromHome='" + isIsWorkingFromHome() + "'" +
                ", bankOffice='" + getBankOffice() + "'" +
                ", isCreditAvailable='" + isIsCreditAvailable() + "'" +
                ", salary='" + getSalary() + "'" +
                "}";
    }

    public EmployeeJob getJob() {
        return this.job;
    }

    public void setJob(EmployeeJob job) {
        this.job = job;
    }

    public Bank getBank() {
        return this.bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public boolean isIsWorkingFromHome() {
        return this.isWorkingFromHome;
    }

    public boolean getIsWorkingFromHome() {
        return this.isWorkingFromHome;
    }

    public void setIsWorkingFromHome(boolean isWorkingFromHome) {
        this.isWorkingFromHome = isWorkingFromHome;
    }

    public BankOffice getBankOffice() {
        return this.bankOffice;
    }

    public void setBankOffice(BankOffice bankOffice) {
        this.bankOffice = bankOffice;
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

    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    private void initWithDefaults() {
        job = null;
        bank = null;
        isWorkingFromHome = false;
        bankOffice = null;
        isCreditAvailable = false;
        salary = null;
    }

}

enum EmployeeJob {
    Manager,
    Worker
}
