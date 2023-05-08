package ua.aleksenko.authorizationservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ua.aleksenko.authorizationservice.model.dto.AuthenticationResponseDto;
import ua.aleksenko.authorizationservice.model.dto.RegistrationRequestDto;
import ua.aleksenko.authorizationservice.service.RegistrationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/registration")
public class RegistrationController {

	private final RegistrationService registrationService;

	@PostMapping
	public AuthenticationResponseDto registerUser(@Valid @RequestBody RegistrationRequestDto dto) {
		return registrationService.registerUser(dto);
	}
}
