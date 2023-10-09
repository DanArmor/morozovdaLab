package tech.reliab.course.morozovda.bank.service;

import tech.reliab.course.morozovda.bank.entity.CreditAccount;

public interface CreditAccountService {
    CreditAccount create(CreditAccount creditAccount);

    boolean makeMontlyPayment(CreditAccount creditAccount);
}
