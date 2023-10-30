package tech.reliab.course.morozovda.bank.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import tech.reliab.course.morozovda.bank.entity.BankAtm;
import tech.reliab.course.morozovda.bank.entity.BankOffice;
import tech.reliab.course.morozovda.bank.entity.Employee;

public interface BankOfficeService {
    BankOffice create(BankOffice bankOffice);

    public void printBankOfficeData(UUID id);

    public BankOffice getBankOfficeById(UUID id);

    public List<BankOffice> getAllOffices();

    public List<Employee> getAllEmployeesByOfficeId(UUID id);

    boolean installAtm(UUID id, BankAtm bankAtm);

    boolean removeAtm(BankOffice bankOffice, BankAtm bankAtm);

    boolean depositMoney(BankOffice bankOffice, BigDecimal amount);

    boolean withdrawMoney(BankOffice bankOffice, BigDecimal amount);

    boolean addEmployee(UUID id, Employee employee);

    boolean removeEmployee(BankOffice bankOffice, Employee employee);
}
