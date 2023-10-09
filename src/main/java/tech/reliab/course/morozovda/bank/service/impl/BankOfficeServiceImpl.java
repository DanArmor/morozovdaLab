package tech.reliab.course.morozovda.bank.service.impl;

import java.math.BigDecimal;

import tech.reliab.course.morozovda.bank.entity.BankAtm;
import tech.reliab.course.morozovda.bank.entity.BankOffice;
import tech.reliab.course.morozovda.bank.entity.Employee;
import tech.reliab.course.morozovda.bank.service.BankOfficeService;

public class BankOfficeServiceImpl implements BankOfficeService {

    @Override
    public boolean addEmployee(BankOffice bankOffice, Employee employee) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public BankOffice create(BankOffice bankOffice) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean depositMoney(BankOffice bankOffice, BigDecimal amount) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean installAtm(BankOffice bankOffice, BankAtm bankAtm) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeAtm(BankOffice bankOffice, BankAtm bankAtm) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeEmployee(BankOffice bankOffice, Employee employee) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean withdrawMoney(BankOffice bankOffice, BigDecimal amount) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
