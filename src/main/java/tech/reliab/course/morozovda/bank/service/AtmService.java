package tech.reliab.course.morozovda.bank.service;

import java.math.BigDecimal;
import java.util.List;

import tech.reliab.course.morozovda.bank.entity.BankAtm;
import tech.reliab.course.morozovda.bank.exception.NotEnoughMoneyException;
import tech.reliab.course.morozovda.bank.exception.NotFoundException;
import tech.reliab.course.morozovda.bank.exception.NotUniqueIdException;

public interface AtmService {
    BankAtm create(BankAtm bankAtm) throws NotFoundException, NotUniqueIdException;

    public BankAtm getBankAtmById(int id) throws NotFoundException;

    public List<BankAtm> getAllBankAtms();

    boolean depositMoney(BankAtm bankAtm, BigDecimal amount) throws NotFoundException;

    boolean withdrawMoney(BankAtm bankAtm, BigDecimal amount) throws NotFoundException, NotEnoughMoneyException;

    public boolean isAtmSuitable(BankAtm bankAtm, BigDecimal money);
}
