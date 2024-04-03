package ua.aleksenko.authorizationservice.service;

import ua.aleksenko.authorizationservice.model.dto.AuthenticationResponseDto;
import ua.aleksenko.authorizationservice.model.dto.ResetPasswordDto;

public interface ResetPasswordService {

  AuthenticationResponseDto resetPassword(ResetPasswordDto dto);
}
