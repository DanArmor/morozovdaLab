package tech.reliab.course.morozovda.bank.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tech.reliab.course.morozovda.bank.entity.PaymentAccount;
import tech.reliab.course.morozovda.bank.exception.NotEnoughMoneyException;
import tech.reliab.course.morozovda.bank.exception.NotFoundException;
import tech.reliab.course.morozovda.bank.exception.NotUniqueIdException;
import tech.reliab.course.morozovda.bank.service.ClientService;
import tech.reliab.course.morozovda.bank.service.PaymentAccountService;

public class PaymentAccountServiceImpl implements PaymentAccountService {

    private final Map<Integer, PaymentAccount> paymentAccountsTable = new HashMap<>();
    private final ClientService clientService;

    public PaymentAccountServiceImpl(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public PaymentAccount create(PaymentAccount paymentAccount) throws NotFoundException, NotUniqueIdException {
        if (paymentAccount == null) {
            return null;
        }

        if (paymentAccount.getBalance().signum() < 0) {
            System.err.println("Error: PaymentAccount - payment account balance must be non-negative");
            return null;
        }

        PaymentAccount newAccount = new PaymentAccount(paymentAccount);
        if (paymentAccountsTable.containsKey(paymentAccount.getId())) {
            throw new NotUniqueIdException(paymentAccount.getId());
        }
        paymentAccountsTable.put(newAccount.getId(), newAccount);
        clientService.addPaymentAccount(newAccount.getClient().getId(), newAccount);

        return newAccount;
    }

    @Override
    public boolean depositMoney(PaymentAccount paymentAccount, BigDecimal amount) {
        if (paymentAccount == null) {
            System.err.println("Error: PaymentAccount - non existing payment account");
            return false;
        }

        if (amount.signum() <= 0) {
            System.err.println("Error: PaymentAccount - deposit amount must be positive");
            return false;
        }

        paymentAccount.setBalance(paymentAccount.getBalance().add(amount));
        return true;
    }

    @Override
    public boolean withdrawMoney(PaymentAccount paymentAccount, BigDecimal amount) throws NotEnoughMoneyException {
        if (paymentAccount == null) {
            System.err.println("Error: PaymentAccount - non existing payment account");
            return false;
        }

        if (amount.signum() <= 0) {
            System.err
                    .println("Error: PaymentAccount - withdrawal amount must be positive");
            return false;
        }

        if (paymentAccount.getBalance().compareTo(amount) < 0) {
            System.err.println("Error:PaymentAccount - not enough money");
            throw new NotEnoughMoneyException();
        }

        paymentAccount.setBalance(paymentAccount.getBalance().subtract(amount));

        return true;
    }

    @Override
    public List<PaymentAccount> getAllPaymentAccounts() {
        return new ArrayList<PaymentAccount>(paymentAccountsTable.values());
    }

    @Override
    public PaymentAccount getPaymentAccountById(int id) throws NotFoundException {
        PaymentAccount account = paymentAccountsTable.get(id);
        if (account == null) {
            System.err.println("Payment account with id " + id + " is not found");
            throw new NotFoundException(id);
        }
        return account;
    }

    @Override
    public void printPaymentData(int id) throws NotFoundException {
        PaymentAccount account = getPaymentAccountById(id);
        if (account == null) {
            return;
        }

        System.out.println(account);
    }

    @Override
    public BigDecimal getTotalMoney(int id) throws NotFoundException {
        PaymentAccount paymentAccount = getPaymentAccountById(id);
        return paymentAccount.getBalance();
    }

}
