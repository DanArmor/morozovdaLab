package tech.reliab.course.morozovda.bank.service;

import java.math.BigDecimal;

import tech.reliab.course.morozovda.bank.entity.Bank;
import tech.reliab.course.morozovda.bank.entity.BankOffice;
import tech.reliab.course.morozovda.bank.entity.CreditAccount;
import tech.reliab.course.morozovda.bank.entity.Employee;
import tech.reliab.course.morozovda.bank.entity.User;

public interface BankService {
    Bank create(Bank bank);

    boolean addOffice(Bank bank, BankOffice bankOffice);

    boolean removeOffice(Bank bank, BankOffice bankOffice);

    boolean addEmployee(Bank bank, Employee employee);

    boolean removeEmployee(Bank bank, Employee employee);

    boolean addUser(Bank bank, User user);

    boolean removeUser(Bank bank, User user);

    BigDecimal calculateInterestRate(Bank bank);

    boolean depositMoney(Bank bank, BigDecimal amount);

    boolean withdrawMoney(Bank bank, BigDecimal amount);

    boolean approveCredit(Bank bank, CreditAccount account, Employee employee);
}
