package ua.aleksenko.authorizationservice.service;

import ua.aleksenko.authorizationservice.model.dto.AuthenticationResponseDto;
import ua.aleksenko.authorizationservice.model.dto.RegistrationRequestDto;

public interface RegistrationService {

	AuthenticationResponseDto registerUser(RegistrationRequestDto dto);
}
