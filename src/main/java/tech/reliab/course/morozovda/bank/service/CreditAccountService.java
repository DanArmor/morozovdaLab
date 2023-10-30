package tech.reliab.course.morozovda.bank.service;

import java.util.List;
import java.util.UUID;

import tech.reliab.course.morozovda.bank.entity.CreditAccount;

public interface CreditAccountService {
    CreditAccount create(CreditAccount creditAccount);

    public CreditAccount getCreditAccountById(UUID id);

    public List<CreditAccount> getAllCreditAccounts();

    boolean makeMontlyPayment(CreditAccount creditAccount);
}
