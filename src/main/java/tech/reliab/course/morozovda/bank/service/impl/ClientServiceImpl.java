package tech.reliab.course.morozovda.bank.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tech.reliab.course.morozovda.bank.entity.Client;
import tech.reliab.course.morozovda.bank.entity.CreditAccount;
import tech.reliab.course.morozovda.bank.entity.PaymentAccount;
import tech.reliab.course.morozovda.bank.exception.NoPaymentAccountException;
import tech.reliab.course.morozovda.bank.exception.NotFoundException;
import tech.reliab.course.morozovda.bank.exception.NotUniqueIdException;
import tech.reliab.course.morozovda.bank.service.BankService;
import tech.reliab.course.morozovda.bank.service.ClientService;

public class ClientServiceImpl implements ClientService {
    private final Map<Integer, Client> clientsTable = new HashMap<>();
    private final Map<Integer, List<PaymentAccount>> paymentAccountsByClientIdTable = new HashMap<>();
    private final Map<Integer, List<CreditAccount>> creditAccountsByClientIdTable = new HashMap<>();
    private final BankService bankService;

    public ClientServiceImpl(BankService bankService) {
        this.bankService = bankService;
    }

    @Override
    public Client create(Client client) throws NotFoundException, NotUniqueIdException {
        if (client == null) {
            return null;
        }

        if (client.getBank() == null) {
            System.err.println("Error: Client - must have bank");
            return null;
        }

        Client createdClient = new Client(client);

        createdClient.setMonthlyIncome(createdClient.getMonthlyIncome());
        calculateCreditRating(createdClient);
        if (clientsTable.containsKey(createdClient.getId())
                || paymentAccountsByClientIdTable.containsKey(createdClient.getId())
                || creditAccountsByClientIdTable.containsKey(createdClient.getId())) {
            throw new NotUniqueIdException(createdClient.getId());
        }
        clientsTable.put(createdClient.getId(), createdClient);
        paymentAccountsByClientIdTable.put(createdClient.getId(), new ArrayList<>());
        creditAccountsByClientIdTable.put(createdClient.getId(), new ArrayList<>());
        bankService.addClient(client.getBank().getId(), createdClient);

        return createdClient;
    }

    @Override
    public BigDecimal calculateCreditRating(Client client) {
        client.setCreditRating(
                client.getMonthlyIncome().divide(new BigDecimal("10", MathContext.DECIMAL128).multiply(new BigDecimal("100"))));
        return client.getCreditRating();
    }

    @Override
    public boolean addCreditAccount(int id, CreditAccount account) throws NotFoundException {
        Client client = getClientById(id);
        if (client != null) {
            List<CreditAccount> clientCreditAccounts = creditAccountsByClientIdTable.get(id);
            clientCreditAccounts.add(account);
            return true;
        }
        return false;
    }

    @Override
    public boolean addPaymentAccount(int id, PaymentAccount account) throws NotFoundException {
        Client client = getClientById(id);
        if (client != null) {
            List<PaymentAccount> clientCreditAccounts = paymentAccountsByClientIdTable.get(id);
            clientCreditAccounts.add(account);
            return true;
        }
        return false;
    }

    @Override
    public List<PaymentAccount> getAllPaymentAccountsByClientId(int id) throws NotFoundException {
        return paymentAccountsByClientIdTable.get(getClientById(id).getId());
    }

    @Override
    public List<CreditAccount> getAllCreditAccountsByClientId(int id) throws NotFoundException {
        return creditAccountsByClientIdTable.get(getClientById(id).getId());
    }

    @Override
    public List<Client> getAllClients() {
        return new ArrayList<>(clientsTable.values());
    }

    @Override
    public Client getClientById(int id) throws NotFoundException {
        Client client = clientsTable.get(id);
        if (client == null) {
            System.err.println("Client with id " + id + " is not found");
            throw new NotFoundException(id);
        }
        return client;
    }

    @Override
    public void printClientData(int id, boolean withAccounts) throws NotFoundException {
        Client client = getClientById(id);

        if (client == null) {
            return;
        }

        System.out.println(client);
        if (withAccounts) {
            List<PaymentAccount> paymentAccounts = getAllPaymentAccountsByClientId(id);
            if (paymentAccounts != null) {
                System.out.println("Payment accounts:");
                paymentAccounts.forEach((PaymentAccount account) -> {
                    System.out.println(account);
                });
            }
            List<CreditAccount> creditAccounts = getAllCreditAccountsByClientId(id);
            if (creditAccounts != null) {
                System.out.println("Credit accounts:");
                creditAccounts.forEach((CreditAccount account) -> {
                    System.out.println(account);
                });
            }
        }

    }

    @Override
    public PaymentAccount getBestPaymentAccount(int id) throws NotFoundException, NoPaymentAccountException {
        List<PaymentAccount> paymentAccounts = getAllPaymentAccountsByClientId(id);
        PaymentAccount paymentAccount = paymentAccounts
                .stream()
                .min(Comparator.comparing(PaymentAccount::getBalance))
                .orElseThrow(NoPaymentAccountException::new);
        return paymentAccount;
    }

}
