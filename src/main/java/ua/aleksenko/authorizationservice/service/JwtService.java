package ua.aleksenko.authorizationservice.service;

import ua.aleksenko.authorizationservice.model.entity.User;

public interface JwtService {

	String generateToken(User user);

	void validateToken(String token);
}
