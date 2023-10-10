package tech.reliab.course.morozovda.bank.service;

import java.math.BigDecimal;

import tech.reliab.course.morozovda.bank.entity.Bank;
import tech.reliab.course.morozovda.bank.entity.BankOffice;
import tech.reliab.course.morozovda.bank.entity.CreditAccount;
import tech.reliab.course.morozovda.bank.entity.Employee;
import tech.reliab.course.morozovda.bank.entity.Client;

public interface BankService {
    // Создание банка
    Bank create(Bank bank);

    // Добавление офиса
    boolean addOffice(Bank bank, BankOffice bankOffice);

    // Удаление офиса
    boolean removeOffice(Bank bank, BankOffice bankOffice);

    // Добавление сотрудника
    boolean addEmployee(Bank bank, Employee employee);

    // Удаление сотрудника
    boolean removeEmployee(Bank bank, Employee employee);

    // Добавление клиента
    boolean addClient(Bank bank, Client client);

    // Удаление клиента
    boolean removeClient(Bank bank, Client client);

    // Расчет процентной ставки банка
    BigDecimal calculateInterestRate(Bank bank);

    // Внести amount денег в банк
    boolean depositMoney(Bank bank, BigDecimal amount);

    // Вывести amount денег из банка
    boolean withdrawMoney(Bank bank, BigDecimal amount);

    // Оформление заявки на кредит
    boolean approveCredit(Bank bank, CreditAccount account, Employee employee);
}
