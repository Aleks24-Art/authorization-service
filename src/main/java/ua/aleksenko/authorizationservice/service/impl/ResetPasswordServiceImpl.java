package ua.aleksenko.authorizationservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.aleksenko.authorizationservice.model.dto.AuthenticationResponseDto;
import ua.aleksenko.authorizationservice.model.dto.ResetPasswordDto;
import ua.aleksenko.authorizationservice.model.entity.User;
import ua.aleksenko.authorizationservice.model.exception.UserNotFoundException;
import ua.aleksenko.authorizationservice.repository.UserRepository;
import ua.aleksenko.authorizationservice.service.JwtService;
import ua.aleksenko.authorizationservice.service.ResetPasswordService;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResetPasswordServiceImpl implements ResetPasswordService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  @Transactional
  @Override
  public AuthenticationResponseDto resetPassword(ResetPasswordDto dto) {
    log.info("Start resetting password for user: {}", dto.email());
    User user = userRepository.findByEmailIgnoreCase(dto.email())
        .orElseThrow(() -> new UserNotFoundException(dto.email()));
    user.setPassword(passwordEncoder.encode(dto.password()));
    log.info("Finish resetting password for user: {}", dto.email());
    return new AuthenticationResponseDto(jwtService.generateToken(user));
  }
}
