package tech.reliab.course.morozovda.bank.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import tech.reliab.course.morozovda.bank.entity.BankAtm;
import tech.reliab.course.morozovda.bank.entity.BankOffice;
import tech.reliab.course.morozovda.bank.entity.Employee;
import tech.reliab.course.morozovda.bank.service.BankOfficeService;
import tech.reliab.course.morozovda.bank.service.BankService;

public class BankOfficeServiceImpl implements BankOfficeService {

    private final Map<UUID, BankOffice> bankOfficesTable = new HashMap<>();
    private final Map<UUID, List<Employee>> employeesByOfficeIdTable = new HashMap<>();
    private final Map<UUID, List<BankAtm>> atmsByOfficeIdTable = new HashMap<>();
    private final BankService bankService;

    @Override
    public List<Employee> getAllEmployeesByOfficeId(UUID id) {
        return employeesByOfficeIdTable.get(id);
    }

    @Override
    public List<BankOffice> getAllOffices() {
        return new ArrayList<BankOffice>(bankOfficesTable.values());
    }

    @Override
    public BankOffice getBankOfficeById(UUID id) {
        BankOffice office = bankOfficesTable.get(id);
        if (office == null) {
            System.err.println("Office with id " + id.toString() + " is not found");
        }
        return office;
    }

    @Override
    public void printBankOfficeData(UUID id) {
        BankOffice bankOffice = getBankOfficeById(id);
        if (bankOffice == null) {
            return;
        }
        System.out.println("=====================");
        System.out.println(bankOffice);
        List<Employee> employees = getAllEmployeesByOfficeId(id);
        if (employees != null) {
            System.out.println("Employees:");
            employees.forEach((Employee employee) -> {
                System.out.println(employee);
            });
        }
        List<BankAtm> atms = atmsByOfficeIdTable.get(id);
        if (atms != null) {
            System.out.println("Atms:");
            atms.forEach((BankAtm atm) -> {
                System.out.println(atm);
            });
        }
        System.out.println("=====================");
    }

    public BankOfficeServiceImpl(BankService bankService) {
        this.bankService = bankService;
    }

    @Override
    public BankOffice create(BankOffice bankOffice) {
        if (bankOffice == null) {
            return null;
        }

        if (bankOffice.getTotalMoney().signum() < 0) {
            System.err.println("Error: BankOffice - total money must be non-negative");
            return null;
        }

        if (bankOffice.getBank() == null) {
            System.err.println("Error: BankOffice - must have bank");
            return null;
        }

        if (bankOffice.getRentPrice().signum() < 0) {
            System.err.println("Error: BankOffice - rentPrice must be non-negative");
            return null;
        }

        BankOffice newOffice = new BankOffice(bankOffice);
        bankOfficesTable.put(bankOffice.getId(), bankOffice);
        employeesByOfficeIdTable.put(bankOffice.getId(), new ArrayList<>());
        atmsByOfficeIdTable.put(bankOffice.getId(), new ArrayList<>());
        bankService.addOffice(bankOffice.getBank().getId(), bankOffice);

        return newOffice;
    }

    @Override
    public boolean depositMoney(BankOffice bankOffice, BigDecimal amount) {
        if (bankOffice == null) {
            System.err.println("Error: BankOffice - cannot deposit money to not existing office");
            return false;
        }

        if (amount.signum() <= 0) {
            System.err.println("Error: BankOffice - cannot deposit money - deposit amount must be positive");
            return false;
        }

        if (!bankOffice.getIsCashDepositAvailable()) {
            System.err.println("Error: BankOffice - cannot deposit money - office cannot accept deposit");
            return false;
        }

        bankOffice.setTotalMoney(bankOffice.getTotalMoney().add(amount));
        // TODO: Добавить механизм взаимодействия с банком

        return true;
    }

    @Override
    public boolean withdrawMoney(BankOffice bankOffice, BigDecimal amount) {
        if (bankOffice == null) {
            System.err.println("Error: BankOffice - cannot withdraw money from not existing office");
            return false;
        }

        if (amount.signum() <= 0) {
            System.err.println("Error: BankOffice - cannot withdraw money - withdraw amount must be positive");
            return false;
        }

        if (!bankOffice.getIsCashWithdrawalAvailable()) {
            System.err.println("Error: BankOffice - cannot withdraw money - office cannot give withdrawal");
            return false;
        }

        if (bankOffice.getTotalMoney().compareTo(amount) < 0) {
            System.err.println("Error: BankOffice - cannot withdraw money - office does not have enough money");
            return false;
        }

        bankOffice.setTotalMoney(bankOffice.getTotalMoney().subtract(amount));
        // TODO: Добавить механизм взаимодействия с банком

        return true;
    }

    @Override
    public boolean installAtm(UUID id, BankAtm bankAtm) {
        BankOffice bankOffice = getBankOfficeById(id);
        if (bankOffice != null && bankAtm != null) {
            if (!bankOffice.getIsAtmPlaceable()) {
                System.err.println("Error: BankOffice - cannot install atm");
                return false;
            }

            bankOffice.setAtmCount(bankOffice.getAtmCount() + 1);
            bankOffice.getBank().setAtmCount(bankOffice.getBank().getAtmCount() + 1);
            bankAtm.setBankOffice(bankOffice);
            bankAtm.setAddress(bankOffice.getAddress());
            bankAtm.setBank(bankOffice.getBank());
            List<BankAtm> officeAtms = atmsByOfficeIdTable.get(bankOffice.getId());
            officeAtms.add(bankAtm);

            return true;
        }
        return false;
    }

    @Override
    public boolean removeAtm(BankOffice bankOffice, BankAtm bankAtm) {
        if (bankOffice != null && bankAtm != null) {
            // TODO: Добавить механизм взаимодействия с банком (поиск и удаление
            // соответствующего ATM из него)
            final int newAtmCountOffice = bankOffice.getAtmCount() - 1;
            if (newAtmCountOffice < 0) {
                System.err.println("Error: BankOffice - cannot remove ATM, no ATMs");
                return false;
            }

            bankOffice.setAtmCount(newAtmCountOffice);
            return true;
        }
        return false;
    }

    @Override
    public boolean addEmployee(UUID id, Employee employee) {
        BankOffice bankOffice = getBankOfficeById(id);
        if (bankOffice != null && employee != null) {
            employee.setBankOffice(bankOffice);
            employee.setBank(bankOffice.getBank());
            List<Employee> officeEmployees = employeesByOfficeIdTable.get(bankOffice.getId());
            officeEmployees.add(employee);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeEmployee(BankOffice bankOffice, Employee employee) {
        // TODO: добавить механизм поиска и удаления работника из офиса и банка
        if (bankOffice != null && employee != null) {
            return true;
        }
        return false;

    }

}
