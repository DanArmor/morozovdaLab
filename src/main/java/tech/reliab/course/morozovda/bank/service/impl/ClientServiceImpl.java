package tech.reliab.course.morozovda.bank.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import tech.reliab.course.morozovda.bank.entity.Client;
import tech.reliab.course.morozovda.bank.entity.CreditAccount;
import tech.reliab.course.morozovda.bank.entity.PaymentAccount;
import tech.reliab.course.morozovda.bank.service.BankService;
import tech.reliab.course.morozovda.bank.service.ClientService;
import tech.reliab.course.morozovda.bank.utils.BigRandom;

public class ClientServiceImpl implements ClientService {
    private final Map<UUID, Client> clientsTable = new HashMap<>();
    private final Map<UUID, List<PaymentAccount>> paymentAccountsByClientIdTable = new HashMap<>();
    private final Map<UUID, List<CreditAccount>> creditAccountsByClientIdTable = new HashMap<>();
    private final BankService bankService;

    public ClientServiceImpl(BankService bankService) {
        this.bankService = bankService;
    }

    @Override
    public Client create(Client client) {
        if (client == null) {
            return null;
        }

        if (client.getBank() == null) {
            System.err.println("Error: Client - must have bank");
            return null;
        }

        Client createdClient = new Client(client);

        final BigDecimal monthlyIncome = BigRandom.between(new BigDecimal("0.0"), new BigDecimal("1.0"))
                .multiply(Client.MAX_MONTHLY_INCOME);
        createdClient.setMonthlyIncome(monthlyIncome);
        calculateCreditRating(createdClient);

        clientsTable.put(createdClient.getId(), createdClient);
        paymentAccountsByClientIdTable.put(createdClient.getId(), new ArrayList<>());
        creditAccountsByClientIdTable.put(createdClient.getId(), new ArrayList<>());
        bankService.addClient(client.getBank().getId(), createdClient);

        return createdClient;
    }

    @Override
    public BigDecimal calculateCreditRating(Client client) {
        client.setCreditRating(
                client.getMonthlyIncome().divide(new BigDecimal("1000").multiply(new BigDecimal("100"))));
        return client.getCreditRating();
    }

    @Override
    public boolean addCreditAccount(UUID id, CreditAccount account) {
        Client client = clientsTable.get(id);
        if (client != null) {
            List<CreditAccount> clientCreditAccounts = creditAccountsByClientIdTable.get(id);
            clientCreditAccounts.add(account);
            return true;
        }
        return false;
    }

    @Override
    public boolean addPaymentAccount(UUID id, PaymentAccount account) {
        Client client = clientsTable.get(id);
        if (client != null) {
            List<PaymentAccount> clientCreditAccounts = paymentAccountsByClientIdTable.get(id);
            clientCreditAccounts.add(account);
            return true;
        }
        return false;
    }

    @Override
    public List<PaymentAccount> getAllPaymentAccountsByClientId(UUID id) {
        return paymentAccountsByClientIdTable.get(id);
    }

    @Override
    public List<CreditAccount> getAllCreditAccountsByClientId(UUID id) {
        return creditAccountsByClientIdTable.get(id);
    }

    @Override
    public List<Client> getAllClients() {
        return new ArrayList<>(clientsTable.values());
    }

    @Override
    public Client getClientById(UUID id) {
        Client client = clientsTable.get(id);
        if (client == null) {
            System.err.println("Client with id " + id.toString() + " is not found");
        }
        return client;
    }

    @Override
    public void printClientData(UUID id, boolean withAccounts) {
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

}
