package tech.reliab.course.morozovda.bank.service.impl;

import java.math.BigDecimal;

import tech.reliab.course.morozovda.bank.entity.Bank;
import tech.reliab.course.morozovda.bank.entity.BankOffice;
import tech.reliab.course.morozovda.bank.entity.Client;
import tech.reliab.course.morozovda.bank.entity.CreditAccount;
import tech.reliab.course.morozovda.bank.entity.Employee;
import tech.reliab.course.morozovda.bank.service.BankService;

public class BankServiceImpl implements BankService {

    @Override
    public boolean addEmployee(Bank bank, Employee employee) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addOffice(Bank bank, BankOffice bankOffice) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addClient(Bank bank, Client client) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean approveCredit(Bank bank, CreditAccount account, Employee employee) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public BigDecimal calculateInterestRate(Bank bank) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Bank create(Bank bank) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean depositMoney(Bank bank, BigDecimal amount) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeEmployee(Bank bank, Employee employee) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeOffice(Bank bank, BankOffice bankOffice) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeClient(Bank bank, Client client) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean withdrawMoney(Bank bank, BigDecimal amount) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
