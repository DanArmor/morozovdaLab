package tech.reliab.course.morozovda.bank.service;

import java.util.List;

import tech.reliab.course.morozovda.bank.entity.CreditAccount;

public interface CreditAccountService {
    CreditAccount create(CreditAccount creditAccount);

    public CreditAccount getCreditAccountById(int id);

    public List<CreditAccount> getAllCreditAccounts();

    boolean makeMontlyPayment(CreditAccount creditAccount);
}
