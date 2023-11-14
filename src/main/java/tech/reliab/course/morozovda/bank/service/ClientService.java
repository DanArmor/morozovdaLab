package tech.reliab.course.morozovda.bank.service;

import java.math.BigDecimal;
import java.util.List;

import tech.reliab.course.morozovda.bank.entity.Client;
import tech.reliab.course.morozovda.bank.entity.CreditAccount;
import tech.reliab.course.morozovda.bank.entity.PaymentAccount;

public interface ClientService {
    Client create(Client client);

    public void printClientData(int id, boolean withAccounts);

    public Client getClientById(int id);

    public List<Client> getAllClients();

    public boolean addPaymentAccount(int id, PaymentAccount account);

    public boolean addCreditAccount(int id, CreditAccount account);

    public List<PaymentAccount> getAllPaymentAccountsByClientId(int id);

    public List<CreditAccount> getAllCreditAccountsByClientId(int id);

    BigDecimal calculateCreditRating(Client client);

    public PaymentAccount getBestPaymentAccount(int id);
}
