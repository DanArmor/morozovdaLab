package tech.reliab.course.morozovda.bank.service;

import tech.reliab.course.morozovda.bank.entity.Client;

public interface ClientService {
    Client create(Client client);

    int calculateCreditRating(Client client);
}
