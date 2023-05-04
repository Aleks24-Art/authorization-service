package ua.aleksenko.authorizationservice.service;

import ua.aleksenko.authorizationservice.model.dto.RegistrationRequestDto;

public interface RegistrationService {

	void registerUser(RegistrationRequestDto dto);
}
