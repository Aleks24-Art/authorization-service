package ua.aleksenko.authorizationservice.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.aleksenko.authorizationservice.model.dto.AuthenticationResponseDto;
import ua.aleksenko.authorizationservice.model.dto.LoginRequestDto;
import ua.aleksenko.authorizationservice.model.entity.User;
import ua.aleksenko.authorizationservice.service.JwtService;
import ua.aleksenko.authorizationservice.service.LoginService;
import ua.aleksenko.authorizationservice.service.UserService;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final UserService userService;

	@Override
	@Transactional
	public AuthenticationResponseDto login(LoginRequestDto dto) {
		log.info("Start login for user: {}", dto.email());
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.email(), dto.password()));
		User user = userService.findUser(dto.email());
		return new AuthenticationResponseDto(jwtService.generateToken(user));
	}
}
