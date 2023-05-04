package ua.aleksenko.authorizationservice.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
}
