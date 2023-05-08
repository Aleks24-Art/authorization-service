package ua.aleksenko.authorizationservice.runner;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.aleksenko.authorizationservice.model.entity.Role;
import ua.aleksenko.authorizationservice.model.entity.User;
import ua.aleksenko.authorizationservice.repository.UserRepository;

@Profile("!test")
@Component
@Slf4j
@RequiredArgsConstructor
public class AdminCreatorRunner implements ApplicationRunner {

	@Value(value = "${admin.email}")
	private String adminEmail;

	@Value(value = "${admin.password}")
	private String adminPassword;

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public void run(ApplicationArguments args) {
		Optional<User> userOptional = userRepository.findByEmailIgnoreCase(adminEmail);
		if (userOptional.isEmpty()) {
			userRepository.save(
					new User("Admin",
							"Admin",
							adminEmail,
							passwordEncoder.encode(adminPassword),
							Role.ADMIN)
			);
			log.info("Admin successfully created!");
		}
	}
}
