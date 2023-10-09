package tech.reliab.course.morozovda.bank.service.impl;

import java.math.BigDecimal;

import tech.reliab.course.morozovda.bank.entity.PaymentAccount;
import tech.reliab.course.morozovda.bank.service.PaymentAccountService;

public class PaymentAccountServiceImpl implements PaymentAccountService {

    @Override
    public PaymentAccount create(PaymentAccount paymentAccount) {
        if (paymentAccount == null) {
            return null;
        }

        if (paymentAccount.getBalance().signum() < 0) {
            System.err.println("Error: PaymentAccount - payment account balance must be non-negative");
            return null;
        }

        return new PaymentAccount(paymentAccount);
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
    public boolean withdrawMoney(PaymentAccount paymentAccount, BigDecimal amount) {
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
            return false;
        }

        paymentAccount.setBalance(paymentAccount.getBalance().subtract(amount));

        return true;
    }

}
