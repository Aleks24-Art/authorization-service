package ua.aleksenko.authorizationservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.aleksenko.authorizationservice.model.dto.UserDto;
import ua.aleksenko.authorizationservice.model.entity.User;
import ua.aleksenko.authorizationservice.model.exception.UserNotFoundException;
import ua.aleksenko.authorizationservice.repository.UserRepository;
import ua.aleksenko.authorizationservice.service.UserService;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public User findUser(String email) {
		log.info("Try to find user: {}", email);
		return userRepository.findByEmailIgnoreCase(email)
				.orElseThrow(() -> new UserNotFoundException(email));
	}

	@Override
	@Transactional(readOnly = true)
	public UserDto findUserDto(String email) {
		log.info("Try to find user: {}", email);
		User user = userRepository.findByEmailIgnoreCase(email)
				.orElseThrow(() -> new UserNotFoundException(email));
		return userToDto(user);
	}

	@Override
	@Transactional
	public UserDto updateUser(User userToUpdate) {
		User user = userRepository.findByEmailIgnoreCase(userToUpdate.getEmail())
				.orElseThrow(() -> new UserNotFoundException(userToUpdate.getEmail()));
		user.setFirstName(userToUpdate.getFirstName());
		user.setLastName(userToUpdate.getLastName());
		user.setAge(userToUpdate.getAge());
		user.setAddress(userToUpdate.getAddress());
		user.setWebsite(userToUpdate.getWebsite());
		user.setGender(userToUpdate.getGender());
		return userToDto(userRepository.save(user));
	}

	private UserDto userToDto(User user) {
		return new UserDto(
				user.getFirstName(),
				user.getLastName(),
				user.getAge(),
				user.getGender(),
				user.getAddress(),
				user.getWebsite(),
				user.getEmail()
		);
	}
}
