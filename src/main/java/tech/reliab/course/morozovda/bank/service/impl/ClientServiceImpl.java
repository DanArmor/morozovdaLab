package tech.reliab.course.morozovda.bank.service.impl;

import java.math.BigDecimal;

import tech.reliab.course.morozovda.bank.entity.Client;
import tech.reliab.course.morozovda.bank.service.ClientService;
import tech.reliab.course.morozovda.bank.utils.BigRandom;

public class ClientServiceImpl implements ClientService {

    @Override
    public BigDecimal calculateCreditRating(Client client) {
        client.setCreditRating(
                client.getMonthlyIncome().divide(new BigDecimal("1000").multiply(new BigDecimal("100"))));
        return client.getCreditRating();
    }

    @Override
    public Client create(Client client) {
        if (client == null) {
            return null;
        }

        if (client.getBank() == null) {
            System.out.println("Error: Client - must have bank");
            return null;
        }

        Client createdclient = new Client(client);

        final BigDecimal monthlyIncome = BigRandom.between(new BigDecimal("0.0"), new BigDecimal("1.0"))
                .multiply(Client.MAX_MONTHLY_INCOME);
        createdclient.setMonthlyIncome(monthlyIncome);
        calculateCreditRating(createdclient);

        return createdclient;
    }

}
