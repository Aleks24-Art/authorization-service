package ua.aleksenko.authorizationservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.aleksenko.authorizationservice.service.JwtService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/token")
public class TokenController {

	private final JwtService jwtService;

	@GetMapping("/validate")
	public void validateToken(@RequestParam String token) {
		log.info("Try to validate token: {}", token);
		jwtService.validateToken(token);
		log.info("Valid");
	}
}
