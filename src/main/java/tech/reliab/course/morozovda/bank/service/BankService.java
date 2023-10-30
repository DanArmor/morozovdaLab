package tech.reliab.course.morozovda.bank.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import tech.reliab.course.morozovda.bank.entity.Bank;
import tech.reliab.course.morozovda.bank.entity.BankOffice;
import tech.reliab.course.morozovda.bank.entity.CreditAccount;
import tech.reliab.course.morozovda.bank.entity.Employee;
import tech.reliab.course.morozovda.bank.entity.Client;

public interface BankService {
    // Создание банка
    public Bank create(Bank bank);

    // Возвращает банк по его ID
    public Bank getBankById(UUID bankId);

    // Установка сервиса для управления офисами
    public void setBankOfficeService(BankOfficeService bankOfficeService);

    // Получение всех офисов банка с заданным ID
    public List<BankOffice> getAllOfficesByBankId(UUID id);

    // Установка сервиса для управления клиентами
    public void setClientService(ClientService bankOfficeService);

    // Удаляет банк по его ID
    public boolean deleteBankById(UUID bankId);

    // Возвращает все банки
    public List<Bank> getAllBanks();

    // Вывод данных о банке с заданным ID
    public void printBankData(UUID bankId);

    // Добавление офиса
    public boolean addOffice(UUID bankId, BankOffice bankOffice);

    // Удаление офиса
    public boolean removeOffice(UUID bankId, BankOffice bankOffice);

    // Добавление сотрудника
    public boolean addEmployee(Bank bank, Employee employee);

    // Удаление сотрудника
    public boolean removeEmployee(Bank bank, Employee employee);

    // Добавление клиента
    public boolean addClient(UUID id, Client client);

    // Удаление клиента
    public boolean removeClient(Bank bank, Client client);

    // Расчет процентной ставки банка
    public BigDecimal calculateInterestRate(Bank bank);

    // Внести amount денег в банк
    public boolean depositMoney(UUID id, BigDecimal amount);

    // Вывести amount денег из банка
    public boolean withdrawMoney(UUID id, BigDecimal amount);

    // Оформление заявки на кредит
    public boolean approveCredit(Bank bank, CreditAccount account, Employee employee);
}
