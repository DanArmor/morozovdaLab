package tech.reliab.course.morozovda.bank.service;

import tech.reliab.course.morozovda.bank.entity.User;

public interface UserService {
    User create(User user);

    int calculateCreditRating(User user);
}
