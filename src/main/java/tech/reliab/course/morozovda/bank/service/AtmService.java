package tech.reliab.course.morozovda.bank.service;

import java.math.BigDecimal;

import tech.reliab.course.morozovda.bank.entity.BankAtm;

public interface AtmService {
    BankAtm create(BankAtm bankAtm);

    boolean depositMoney(BankAtm bankAtm, BigDecimal amount);

    boolean withdrawMoney(BankAtm bankAtm, BigDecimal amount);
}
