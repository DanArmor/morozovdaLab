package tech.reliab.course.morozovda.bank.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tech.reliab.course.morozovda.bank.entity.BankAtm;
import tech.reliab.course.morozovda.bank.entity.BankOffice;
import tech.reliab.course.morozovda.bank.entity.Employee;
import tech.reliab.course.morozovda.bank.exception.NotFoundException;
import tech.reliab.course.morozovda.bank.exception.NotUniqueIdException;
import tech.reliab.course.morozovda.bank.service.AtmService;
import tech.reliab.course.morozovda.bank.service.BankOfficeService;
import tech.reliab.course.morozovda.bank.service.BankService;
import tech.reliab.course.morozovda.bank.service.EmployeeService;

public class BankOfficeServiceImpl implements BankOfficeService {
    private final Map<Integer, BankOffice> bankOfficesTable = new HashMap<>();
    private final Map<Integer, List<Employee>> employeesByOfficeIdTable = new HashMap<>();
    private final Map<Integer, List<BankAtm>> atmsByOfficeIdTable = new HashMap<>();
    private final BankService bankService;
    private EmployeeService employeeService;
    private AtmService atmService;

    @Override
    public List<Employee> getAllEmployeesByOfficeId(int id) {
        return employeesByOfficeIdTable.get(id);
    }

    @Override
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void setAtmService(AtmService atmService) {
        this.atmService = atmService;
    }

    @Override
    public List<BankOffice> getAllOffices() {
        return new ArrayList<BankOffice>(bankOfficesTable.values());
    }

    @Override
    public BankOffice getBankOfficeById(int id) {
        BankOffice office = bankOfficesTable.get(id);
        if (office == null) {
            System.err.println("Office with id " + id + " is not found");
        }
        return office;
    }

    @Override
    public void printBankOfficeData(int id) {
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
        List<BankAtm> atms = getAllOfficeAtms(bankOffice.getId());
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
    public BankOffice create(BankOffice bankOffice) throws NotFoundException, NotUniqueIdException {
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
        bankOffice.getBank().setTotalMoney(bankOffice.getBank().getTotalMoney().add(amount));

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
        bankOffice.getBank().setTotalMoney(bankOffice.getBank().getTotalMoney().subtract(amount));

        return true;
    }

    @Override
    public boolean installAtm(int id, BankAtm bankAtm) {
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
            depositMoney(bankOffice, bankAtm.getTotalMoney());

            return true;
        }
        return false;
    }

    @Override
    public boolean removeAtm(int id, BankAtm bankAtm) {
        BankOffice bankOffice = getBankOfficeById(id);
        if (bankOffice != null && bankAtm != null) {
            final int newAtmCountOffice = bankOffice.getAtmCount() - 1;
            if (newAtmCountOffice < 0) {
                System.err.println("Error: BankOffice - cannot remove ATM, no ATMs");
                return false;
            }
            bankOffice.setAtmCount(newAtmCountOffice);
            bankOffice.getBank().setAtmCount(bankOffice.getBank().getAtmCount() - 1);
            List<BankAtm> officeAtms = atmsByOfficeIdTable.get(bankOffice.getId());
            officeAtms.remove(bankAtm);
            bankAtm.setBankOffice(null);
            bankAtm.setAddress("");
            bankAtm.setBank(null);
            withdrawMoney(bankOffice, bankAtm.getTotalMoney());
            return true;
        }
        return false;
    }

    @Override
    public boolean addEmployee(int id, Employee employee) {
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

    private List<BankAtm> getAllOfficeAtms(int id) {
        return atmsByOfficeIdTable.get(id);
    }

    @Override
    public List<BankAtm> getSuitableBankAtmInOffice(BankOffice bankOffice, BigDecimal money) {
        List<BankAtm> bankAtmByOffice = getAllOfficeAtms(bankOffice.getId());
        List<BankAtm> suitableBankAtm = new ArrayList<>();

        for (BankAtm bankAtm : bankAtmByOffice) {
            if (atmService.isAtmSuitable(bankAtm, money)) {
                suitableBankAtm.add(bankAtm);
            }
        }

        return suitableBankAtm;
    }

    @Override
    public List<Employee> getSuitableEmployeeInOffice(BankOffice bankOffice) {
        List<Employee> employees = getAllEmployeesByOfficeId(bankOffice.getId());
        List<Employee> suitableEmployee = new ArrayList<>();

        for (Employee employee : employees) {
            if (employeeService.isEmployeeSuitable(employee)) {
                suitableEmployee.add(employee);
            }
        }

        return suitableEmployee;
    }

    @Override
    public boolean isSuitableBankOffice(BankOffice bankOffice, BigDecimal money) {
        if (bankOffice.getIsWorking() && bankOffice.getIsCashWithdrawalAvailable()
                && bankOffice.getTotalMoney().compareTo(money) >= 0) {
            List<BankAtm> bankAtmSuitable = getSuitableBankAtmInOffice(bankOffice, money);
            if (bankAtmSuitable.isEmpty()) {
                return false;
            }

            List<Employee> employeesSuitable = getSuitableEmployeeInOffice(bankOffice);
            if (employeesSuitable.isEmpty()) {
                return false;
            }
            return true;
        }

        return false;
    }

}
