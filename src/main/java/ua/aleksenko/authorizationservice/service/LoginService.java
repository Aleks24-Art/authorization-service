package ua.aleksenko.authorizationservice.service;

import ua.aleksenko.authorizationservice.model.dto.AuthenticationResponseDto;
import ua.aleksenko.authorizationservice.model.dto.LoginRequestDto;

public interface LoginService {

	AuthenticationResponseDto login(LoginRequestDto dto);
}
