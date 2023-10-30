package tech.reliab.course.morozovda.bank.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import tech.reliab.course.morozovda.bank.entity.BankAtm;

public interface AtmService {
    BankAtm create(BankAtm bankAtm);

    public BankAtm getBankAtmById(UUID id);

    public List<BankAtm> getAllBankAtms();

    boolean depositMoney(BankAtm bankAtm, BigDecimal amount);

    boolean withdrawMoney(BankAtm bankAtm, BigDecimal amount);
}
