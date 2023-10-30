package tech.reliab.course.morozovda.bank.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import tech.reliab.course.morozovda.bank.entity.Account;
import tech.reliab.course.morozovda.bank.entity.Client;
import tech.reliab.course.morozovda.bank.entity.CreditAccount;
import tech.reliab.course.morozovda.bank.entity.PaymentAccount;

public interface ClientService {
    Client create(Client client);

    public void printClientData(UUID id, boolean withAccounts);

    public Client getClientById(UUID id);

    public List<Client> getAllClients();

    public boolean addPaymentAccount(UUID id, PaymentAccount account);

    public boolean addCreditAccount(UUID id, CreditAccount account);

    public List<PaymentAccount> getAllPaymentAccountsByClientId(UUID id);

    public List<CreditAccount> getAllCreditAccountsByClientId(UUID id);

    BigDecimal calculateCreditRating(Client client);
}
