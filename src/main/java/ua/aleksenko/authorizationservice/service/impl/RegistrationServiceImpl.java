package ua.aleksenko.authorizationservice.service.impl;

import java.util.function.Consumer;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.aleksenko.authorizationservice.model.dto.AuthenticationResponseDto;
import ua.aleksenko.authorizationservice.model.dto.RegistrationRequestDto;
import ua.aleksenko.authorizationservice.model.entity.Role;
import ua.aleksenko.authorizationservice.model.entity.User;
import ua.aleksenko.authorizationservice.model.exception.UserAlreadyRegisteredException;
import ua.aleksenko.authorizationservice.repository.UserRepository;
import ua.aleksenko.authorizationservice.service.JwtService;
import ua.aleksenko.authorizationservice.service.RegistrationService;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	@Override
	@Transactional
	public AuthenticationResponseDto registerUser(RegistrationRequestDto dto) {
		log.info("Start registering user: {}", dto.getEmail());
		userRepository.findByEmailIgnoreCase(dto.getEmail())
				.ifPresent(throwUserAlreadyRegisteredException());
		User user = userRepository.save(
				new User(dto.getFirstName(),
						dto.getLastName(),
						dto.getEmail(),
						passwordEncoder.encode(dto.getPassword()),
						Role.USER)
		);
		log.info("Finish registering user: {}", dto.getEmail());
		return new AuthenticationResponseDto(jwtService.generateToken(user));
	}

	public Consumer<User> throwUserAlreadyRegisteredException() {
		return user -> {
			throw new UserAlreadyRegisteredException(user.getEmail());
		};
	}

}
