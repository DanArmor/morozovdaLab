package tech.reliab.course.morozovda.bank.service;

import java.math.BigDecimal;
import java.util.List;

import tech.reliab.course.morozovda.bank.entity.BankAtm;
import tech.reliab.course.morozovda.bank.entity.BankOffice;
import tech.reliab.course.morozovda.bank.entity.Employee;
import tech.reliab.course.morozovda.bank.exception.NotEnoughMoneyException;
import tech.reliab.course.morozovda.bank.exception.NotFoundException;
import tech.reliab.course.morozovda.bank.exception.NotUniqueIdException;

public interface BankOfficeService {
    BankOffice create(BankOffice bankOffice) throws NotFoundException, NotUniqueIdException;

    public void setEmployeeService(EmployeeService employeeService);

    public void setAtmService(AtmService atmService);

    public void printBankOfficeData(int id) throws NotFoundException;

    public BankOffice getBankOfficeById(int id) throws NotFoundException;

    public List<BankOffice> getAllOffices();

    public List<Employee> getAllEmployeesByOfficeId(int id) throws NotFoundException;

    boolean installAtm(int id, BankAtm bankAtm) throws NotFoundException;

    boolean removeAtm(int id, BankAtm bankAtm) throws NotFoundException;

    boolean depositMoney(BankOffice bankOffice, BigDecimal amount);

    boolean withdrawMoney(BankOffice bankOffice, BigDecimal amount) throws NotFoundException, NotEnoughMoneyException;

    boolean addEmployee(int id, Employee employee) throws NotFoundException;

    boolean removeEmployee(BankOffice bankOffice, Employee employee);

    public boolean isSuitableBankOffice(BankOffice bankOffice, BigDecimal money);

    public List<BankAtm> getSuitableBankAtmInOffice(BankOffice bankOffice, BigDecimal money);

    public List<Employee> getSuitableEmployeeInOffice(BankOffice bankOffice);
}
