package ua.aleksenko.authorizationservice.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import lombok.extern.slf4j.Slf4j;
import ua.aleksenko.authorizationservice.model.entity.User;
import ua.aleksenko.authorizationservice.service.JwtService;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {

	@Value(value = "${jwt.secret}")
	private String secretKey;

	@Value(value = "${jwt.validity}")
	private long validityInMilli;

	@Value(value = "${jwt.issuer}")
	private String issuer;

	@Override
	public String generateToken(User user) {
		return JWT.create()
				.withSubject(user.getEmail())
				.withIssuer(issuer)
				.withClaim("role", user.getRole().toString())
				.withExpiresAt(new Date(System.currentTimeMillis() + validityInMilli))
				.withIssuedAt(new Date(System.currentTimeMillis()))
				.sign(Algorithm.HMAC256(secretKey));
	}

	@Override
	public void validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secretKey);
			JWTVerifier verifier = JWT.require(algorithm)
					.withIssuer(issuer)
					.withClaimPresence("role")
					.build();

			verifier.verify(token);
		} catch (JWTVerificationException e1) {
			log.error("Token verification failed: {}", token);
			throw e1;
		} catch (RuntimeException e2) {
			log.error("Error while verification token: {}", token);
			throw e2;
		}
	}
}
