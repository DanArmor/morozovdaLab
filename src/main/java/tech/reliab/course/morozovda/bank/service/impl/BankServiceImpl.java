package tech.reliab.course.morozovda.bank.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import tech.reliab.course.morozovda.bank.entity.Bank;
import tech.reliab.course.morozovda.bank.entity.BankOffice;
import tech.reliab.course.morozovda.bank.entity.Client;
import tech.reliab.course.morozovda.bank.entity.CreditAccount;
import tech.reliab.course.morozovda.bank.entity.Employee;
import tech.reliab.course.morozovda.bank.service.BankService;
import tech.reliab.course.morozovda.bank.utils.BigRandom;

public class BankServiceImpl implements BankService {
    private final Map<UUID, Bank> banksTable = new HashMap<>();
    private final Map<UUID, List<BankOffice>> officesTable = new HashMap<>();

    @Override
    public Bank create(Bank bank) {
        if (bank == null) {
            return null;
        }

        Bank newBank = new Bank(bank.getId(), bank.getName());

        final Random random = new Random();

        newBank.setRating((byte) random.nextInt(Bank.MAX_RATING.intValue() + 1));
        newBank.setTotalMoney(
                BigRandom.between(new BigDecimal("0.0"), new BigDecimal("1.0").multiply(Bank.MAX_TOTAL_MONEY)));
        calculateInterestRate(newBank);

        return newBank;
    }

    @Override
    public boolean addEmployee(Bank bank, Employee employee) {
        if (bank != null && employee != null) {
            employee.setBank(bank);
            bank.setEmployeeCount(bank.getEmployeeCount() + 1);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeEmployee(Bank bank, Employee employee) {
        if (bank != null && employee != null) {
            final int newEmployeeCount = bank.getEmployeeCount() - 1;

            if (newEmployeeCount < 0) {
                System.err.println("Error: Bank - cannot remove employee, no employees");
                return false;
            }

            bank.setEmployeeCount(newEmployeeCount);

            return true;
        }
        return false;
    }

    @Override
    public Bank getBankById(UUID bankId) {
        Bank bank = banksTable.get(bankId);
        if (bank == null) {
            System.err.println("Bank with id " + bankId.toString() + " is not found");
        }
        return bank;
    }

    @Override
    public void printBankData(UUID bankId) {
        Bank bank = getBankById(bankId);
        if (bank == null) {
            return;
        }
        System.out.println("=====================");
        System.out.println(bank);
        System.out.println("Offices");
        officesTable.get(bankId).forEach((BankOffice office) -> {
            System.out.println(office);
        });
        System.out.println("=====================");
    }

    @Override
    public boolean deleteBankById(UUID bankId){
        return true;
    }

    @Override
    public List<Bank> getAllBanks() {
        return new ArrayList<>(banksTable.values());
    }

    @Override
    public boolean addOffice(UUID bankId, BankOffice bankOffice) {
        Bank bank = getBankById(bankId);
        if (bank != null && bankOffice != null) {
            bankOffice.setBank(bank);
            bank.setOfficeCount(bank.getOfficeCount() + 1);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeOffice(UUID bankId, BankOffice bankOffice) {
        Bank bank = getBankById(bankId);
        if (bank != null && bankOffice != null) {
            final int newOfficeCount = bank.getOfficeCount() - 1;

            if (newOfficeCount < 0) {
                System.err.println("Error: Bank - cannot remove office, no offices");
                return false;
            }

            bank.setOfficeCount(newOfficeCount);

            return true;
        }
        return false;
    }

    @Override
    public boolean addClient(Bank bank, Client client) {
        if (bank != null && client != null) {
            client.setBank(bank);
            bank.setClientCount(bank.getClientCount() + 1);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeClient(Bank bank, Client client) {
        if (bank != null && client != null) {
            int newUserCount = bank.getClientCount() - 1;

            if (newUserCount < 0) {
                System.err.println("Error: Bank - cannot remove user, no users");
                return false;
            }

            bank.setClientCount(newUserCount);
            return true;
        }
        return false;
    }

    @Override
    public boolean approveCredit(Bank bank, CreditAccount account, Employee employee) {
        return false; // TODO: Механизм одобрения кредитов будет уточнен позже
    }

    @Override
    public BigDecimal calculateInterestRate(Bank bank) {
        if (bank != null) {
            final BigDecimal rating = BigDecimal.valueOf(bank.getRating());

            final BigDecimal centralBankInterestRate = BigRandom.between(new BigDecimal("0.0"), new BigDecimal("1.0"))
                    .multiply(Bank.MAX_INTEREST_RATE);
            final BigDecimal maxBankInterestRateMargin = Bank.MAX_INTEREST_RATE.subtract(centralBankInterestRate);
            final BigDecimal bankInterestRateMargin = (BigRandom.between(new BigDecimal("0.0"), new BigDecimal("1.0"))
                    .multiply(maxBankInterestRateMargin))
                    .multiply((new BigDecimal("110").subtract(rating).divide(new BigDecimal("100"))));
            final BigDecimal interestRate = centralBankInterestRate.add(bankInterestRateMargin);

            bank.setInterestRate(interestRate);
            return interestRate;
        }
        return new BigDecimal("0");
    }

    @Override
    public boolean depositMoney(Bank bank, BigDecimal amount) {
        if (bank == null) {
            System.err.println("Error: Bank - cannot deposit money to uninitialized bank");
            return false;
        }

        if (amount.signum() <= 0) {
            System.err.println("Error: Bank - cannot deposit money - deposit amount must be positive");
            return false;
        }

        bank.setTotalMoney(bank.getTotalMoney().add(amount));
        return true;
    }

    @Override
    public boolean withdrawMoney(Bank bank, BigDecimal amount) {
        if (bank == null) {
            System.err.println("Error: Bank - cannot withdraw money, bank is null");
            return false;
        }

        if (amount.signum() <= 0) {
            System.err.println("Error: Bank - cannot withdraw money - withdraw amount must be positive");
            return false;
        }

        if (bank.getTotalMoney().compareTo(amount) < 0) {
            System.err.println("Error: Bank - cannot withdraw money - bank does not have enough money");
            return false;
        }

        bank.setTotalMoney(bank.getTotalMoney().subtract(amount));
        return true;

    }

}
