package ua.aleksenko.authorizationservice.service;

import ua.aleksenko.authorizationservice.model.dto.UserDto;
import ua.aleksenko.authorizationservice.model.entity.User;

public interface UserService {

	User findUser(String email);

	UserDto findUserDto(String email);

	UserDto updateUser(User userToUpdate);
}
