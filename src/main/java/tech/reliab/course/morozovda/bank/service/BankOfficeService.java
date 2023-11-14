package tech.reliab.course.morozovda.bank.service;

import java.math.BigDecimal;
import java.util.List;

import tech.reliab.course.morozovda.bank.entity.BankAtm;
import tech.reliab.course.morozovda.bank.entity.BankOffice;
import tech.reliab.course.morozovda.bank.entity.Employee;

public interface BankOfficeService {
    BankOffice create(BankOffice bankOffice);

    public void setEmployeeService(EmployeeService employeeService);

    public void setAtmService(AtmService atmService);

    public void printBankOfficeData(int id);

    public BankOffice getBankOfficeById(int id);

    public List<BankOffice> getAllOffices();

    public List<Employee> getAllEmployeesByOfficeId(int id);

    boolean installAtm(int id, BankAtm bankAtm);

    boolean removeAtm(int id, BankAtm bankAtm);

    boolean depositMoney(BankOffice bankOffice, BigDecimal amount);

    boolean withdrawMoney(BankOffice bankOffice, BigDecimal amount);

    boolean addEmployee(int id, Employee employee);

    boolean removeEmployee(BankOffice bankOffice, Employee employee);

    public boolean isSuitableBankOffice(BankOffice bankOffice, BigDecimal money);

    public List<BankAtm> getSuitableBankAtmInOffice(BankOffice bankOffice, BigDecimal money);

    public List<Employee> getSuitableEmployeeInOffice(BankOffice bankOffice);
}
