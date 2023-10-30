package tech.reliab.course.morozovda.bank.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import tech.reliab.course.morozovda.bank.entity.BankAtm;
import tech.reliab.course.morozovda.bank.service.AtmService;
import tech.reliab.course.morozovda.bank.service.BankOfficeService;

public class AtmServiceImpl implements AtmService {
    private final Map<UUID, BankAtm> atmsTable = new HashMap<>();
    private final BankOfficeService bankOfficeService;

    @Override
    public List<BankAtm> getAllBankAtms() {
        return new ArrayList<>(atmsTable.values());
    }

    @Override
    public BankAtm getBankAtmById(UUID id) {
        BankAtm atm = atmsTable.get(id);
        if (atm == null) {
            System.err.println("Atm with id " + id.toString() + " is not found");
        }
        return atm;
    }

    public AtmServiceImpl(BankOfficeService bankOfficeService) {
        this.bankOfficeService = bankOfficeService;
    }

    @Override
    public BankAtm create(BankAtm bankAtm) {
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
    public boolean withdrawMoney(BankAtm bankAtm, BigDecimal amount) {
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
            return false;
        }
        bankAtm.setTotalMoney(bankAtm.getTotalMoney().subtract(amount));
        // TODO: Добавить механизм взаимодействия с банком и офисом
        return true;
    }

}
