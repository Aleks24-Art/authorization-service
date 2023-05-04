package ua.aleksenko.authorizationservice.service;

import ua.aleksenko.authorizationservice.model.entity.User;

public interface UserService {

	User findUser(String email);
}
