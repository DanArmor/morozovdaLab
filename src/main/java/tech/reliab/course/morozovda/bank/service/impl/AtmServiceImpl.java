package tech.reliab.course.morozovda.bank.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tech.reliab.course.morozovda.bank.entity.BankAtm;
import tech.reliab.course.morozovda.bank.exception.NotEnoughMoneyException;
import tech.reliab.course.morozovda.bank.exception.NotFoundException;
import tech.reliab.course.morozovda.bank.exception.NotUniqueIdException;
import tech.reliab.course.morozovda.bank.service.AtmService;
import tech.reliab.course.morozovda.bank.service.BankOfficeService;

public class AtmServiceImpl implements AtmService {
    private final Map<Integer, BankAtm> atmsTable = new HashMap<>();
    private final BankOfficeService bankOfficeService;

    @Override
    public List<BankAtm> getAllBankAtms() {
        return new ArrayList<>(atmsTable.values());
    }

    @Override
    public BankAtm getBankAtmById(int id) throws NotFoundException {
        BankAtm atm = atmsTable.get(id);
        if (atm == null) {
            System.err.println("Atm with id " + id + " is not found");
            throw new NotFoundException(id);
        }
        return atm;
    }

    public AtmServiceImpl(BankOfficeService bankOfficeService) {
        this.bankOfficeService = bankOfficeService;
    }

    @Override
    public BankAtm create(BankAtm bankAtm) throws NotFoundException, NotUniqueIdException {
        if (bankAtm == null) {
            return null;
        }
        if (bankAtm.getTotalMoney().signum() < 0) {
            System.err.println("Error: cannot create BankAtm - totalMoney must be non-negative");
            return null;
        }
        if (bankAtm.getMaintenanceCost().signum() < 0) {
            System.err.println("Error: cannot create BankAtm - maintenanceCost must be non-negative");
            return null;
        }
        if (bankAtm.getBankOffice() == null) {
            System.err.println("Error: cannot create BankAtm - bankOffice cannot be null");
            return null;
        }
        BankAtm atm = new BankAtm(bankAtm);
        if (atmsTable.containsKey(atm.getId())) {
            throw new NotUniqueIdException(atm.getId());
        }
        atmsTable.put(atm.getId(), atm);
        bankOfficeService.installAtm(atm.getBankOffice().getId(), atm);
        return atm;
    }

    @Override
    public boolean depositMoney(BankAtm bankAtm, BigDecimal amount) {
        if (bankAtm == null) {
            System.err.println("Error: BankAtm cannot deposit money - non existing ATM");
            return false;
        }
        if (amount.signum() <= 0) {
            System.err.println("Error: BankAtm cannot deposit money - amount is not positive");
            return false;
        }
        if (!bankAtm.getIsCashDepositAvailable()) {
            System.err.println("Error: BankAtm cannot deposit money - deposit is not allowed");
            return false;
        }
        bankAtm.setTotalMoney(bankAtm.getTotalMoney().add(amount));
        // TODO: Добавить механизм взаимодействия с банком и офисом
        return true;
    }

    @Override
    public boolean withdrawMoney(BankAtm bankAtm, BigDecimal amount) throws NotEnoughMoneyException {
        if (bankAtm == null) {
            System.err.println("Error: BankAtm cannot withdraw money - non existing ATM");
            return false;
        }
        if (amount.signum() <= 0) {
            System.err.println("Error: BankAtm cannot withdraw money - amount is not positive");
            return false;
        }
        if (!bankAtm.getIsCashDepositAvailable()) {
            System.err.println("Error: BankAtm cannot withdraw money - deposit is not allowed");
            return false;
        }
        if (bankAtm.getTotalMoney().compareTo(amount) < 0) {
            System.err.println("Error: BankAtm cannot withdraw money - ATM does not have enough money");
            throw new NotEnoughMoneyException();
        }
        bankAtm.setTotalMoney(bankAtm.getTotalMoney().subtract(amount));
        // TODO: Добавить механизм взаимодействия с банком и офисом
        return true;
    }

    @Override
    public boolean isAtmSuitable(BankAtm bankAtm, BigDecimal money) {
        return bankAtm.getTotalMoney().compareTo(money) >= 0;
    }

}
