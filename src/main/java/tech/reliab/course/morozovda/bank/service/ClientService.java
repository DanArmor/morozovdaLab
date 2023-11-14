package tech.reliab.course.morozovda.bank.service;

import java.math.BigDecimal;
import java.util.List;

import tech.reliab.course.morozovda.bank.entity.Client;
import tech.reliab.course.morozovda.bank.entity.CreditAccount;
import tech.reliab.course.morozovda.bank.entity.PaymentAccount;
import tech.reliab.course.morozovda.bank.exception.NoPaymentAccount;
import tech.reliab.course.morozovda.bank.exception.NotFoundException;
import tech.reliab.course.morozovda.bank.exception.NotUniqueIdException;

public interface ClientService {
    Client create(Client client) throws NotFoundException, NotUniqueIdException;

    public void printClientData(int id, boolean withAccounts) throws NotFoundException;

    public Client getClientById(int id) throws NotFoundException;

    public List<Client> getAllClients();

    public boolean addPaymentAccount(int id, PaymentAccount account) throws NotFoundException;

    public boolean addCreditAccount(int id, CreditAccount account) throws NotFoundException;

    public List<PaymentAccount> getAllPaymentAccountsByClientId(int id) throws NotFoundException;

    public List<CreditAccount> getAllCreditAccountsByClientId(int id) throws NotFoundException;

    BigDecimal calculateCreditRating(Client client);

    public PaymentAccount getBestPaymentAccount(int id) throws NotFoundException, NoPaymentAccount;
}
