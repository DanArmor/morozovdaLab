package tech.reliab.course.morozovda.bank.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import tech.reliab.course.morozovda.bank.entity.Bank;
import tech.reliab.course.morozovda.bank.entity.Client;
import tech.reliab.course.morozovda.bank.entity.CreditAccount;
import tech.reliab.course.morozovda.bank.exception.AccountTransferException;
import tech.reliab.course.morozovda.bank.exception.NoPaymentAccountException;
import tech.reliab.course.morozovda.bank.exception.ExportException;
import tech.reliab.course.morozovda.bank.exception.NotEnoughMoneyException;
import tech.reliab.course.morozovda.bank.exception.NotFoundException;
import tech.reliab.course.morozovda.bank.exception.NotUniqueIdException;
import tech.reliab.course.morozovda.bank.service.ClientService;
import tech.reliab.course.morozovda.bank.service.BankService;
import tech.reliab.course.morozovda.bank.service.CreditAccountService;

public class CreditAccountServiceImpl implements CreditAccountService {

    private final Map<Integer, CreditAccount> creditAccountsTable = new HashMap<>();
    private final ClientService clientService;
    private final BankService bankService;

    public CreditAccountServiceImpl(ClientService clientService, BankService bankService) {
        this.clientService = clientService;
        this.bankService = bankService;
    }

    @Override
    public CreditAccount create(CreditAccount creditAccount) throws NotFoundException, NotUniqueIdException {
        if (creditAccount == null) {
            return null;
        }

        if (creditAccount.getMonthCount() < 1) {
            System.err.println("Error: CreditAccount - monthCount must be at least 1");
            return null;
        }

        if (creditAccount.getCreditAmount().signum() <= 0) {
            System.err.println("Error: CreditAccount - creditAmount must be positive");
            return null;
        }

        if (creditAccount.getBank() == null) {
            System.err.println("Error: CreditAccount - no bank");
            return null;
        }

        CreditAccount newAccount = new CreditAccount(creditAccount);
        if (creditAccountsTable.containsKey(newAccount.getId())) {
            throw new NotUniqueIdException(newAccount.getId());
        }
        creditAccountsTable.put(newAccount.getId(), newAccount);
        clientService.addCreditAccount(newAccount.getClient().getId(), newAccount);

        return newAccount;
    }

    @Override
    public boolean makeMontlyPayment(CreditAccount creditAccount) throws NotEnoughMoneyException {
        if (creditAccount == null || creditAccount.getPaymentAccount() == null) {
            System.err.println("Error: CreditAccount - no account to take money from");
            return false;
        }

        final BigDecimal monthlyPayment = creditAccount.getMonthlyPayment();
        final BigDecimal paymentAccountBalance = creditAccount.getPaymentAccount().getBalance();

        if (paymentAccountBalance.compareTo(monthlyPayment) < 0) {
            System.err.println("Error: CreditAccount - not enough balance for monthly payment");
            throw new NotEnoughMoneyException();
        }

        creditAccount.getPaymentAccount().setBalance(paymentAccountBalance.subtract(monthlyPayment));
        creditAccount.setRemainingCreditAmount(creditAccount.getRemainingCreditAmount().subtract(monthlyPayment));

        return true;
    }

    @Override
    public List<CreditAccount> getAllCreditAccounts() {
        return new ArrayList<CreditAccount>(creditAccountsTable.values());
    }

    @Override
    public CreditAccount getCreditAccountById(int id) throws NotFoundException {
        CreditAccount account = creditAccountsTable.get(id);
        if (account == null) {
            System.err.println("Credit account with id " + id + " is not found");
            throw new NotFoundException(id);
        }
        return account;
    }

    @Override
    public boolean importAccountsTxtAndTransferToBank(String fileName, int newBankId)
            throws AccountTransferException {
        File file = new File(fileName);
        if (!file.exists())
            throw new AccountTransferException("File not found");

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        JsonReader reader = null;

        try {
            reader = new JsonReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            throw new AccountTransferException(e.toString());
        }

        CreditAccount[] accountsArr = gson.fromJson(reader, CreditAccount[].class);
        List<CreditAccount> accounts = Arrays.asList(accountsArr);

        for (CreditAccount a : accounts) {
            CreditAccount creditAccount = getCreditAccountById(a.getId());
            if (creditAccount.getBank().getId() == newBankId) {
                System.out.println("Account with id: " + creditAccount.getId() + " already belongs to the given bank!");
            } else {
                Bank newBank = bankService.getBankById(newBankId);
                creditAccount.setBank(newBank);
                creditAccount.getPaymentAccount().setBank(newBank);
            }

            Client client = clientService.getClientById(creditAccount.getClient().getId());
            if (client.getBank().getId() != newBankId)
                bankService.transferClient(client, newBankId);
        }

        return true;
    }

    public boolean exportClientAccountsToTxt(int clientId, int bankId) throws ExportException {
        List<CreditAccount> creditAccounts = clientService.getAllCreditAccountsByClientId(clientId);

        if (creditAccounts.size() == 0)
            throw new NoPaymentAccountException();
        try {
            PrintWriter file = new PrintWriter("out.txt");
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();
            file.println(gson.toJson(creditAccounts));
            file.close();
            return true;
        } catch (FileNotFoundException e) {
            throw new ExportException(e.toString());
        }
    }
}
