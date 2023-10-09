package tech.reliab.course.morozovda.bank.service;

import java.math.BigDecimal;

import tech.reliab.course.morozovda.bank.entity.Client;

public interface ClientService {
    Client create(Client client);

    BigDecimal calculateCreditRating(Client client);
}
