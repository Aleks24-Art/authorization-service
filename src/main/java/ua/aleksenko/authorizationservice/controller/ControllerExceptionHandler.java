package ua.aleksenko.authorizationservice.controller;


import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth0.jwt.exceptions.JWTVerificationException;

import lombok.extern.slf4j.Slf4j;
import ua.aleksenko.authorizationservice.model.error.ApiError;
import ua.aleksenko.authorizationservice.model.exception.UserAlreadyRegisteredException;
import ua.aleksenko.authorizationservice.model.exception.UserNotFoundException;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(JWTVerificationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError handleJWTVerificationException(JWTVerificationException e) {
        log.error("JWTVerificationException handled with error: {}", e.getMessage());
        return new ApiError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }

	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ApiError handleBadCredentialsException(BadCredentialsException e) {
		log.error("BadCredentialsException handled with error: {}", e.getMessage());
		return new ApiError(HttpStatus.UNAUTHORIZED.value(), "The email address or password is incorrect!");
	}

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ApiError handleUserNotFoundException(UserNotFoundException e) {
		log.error("UserNotFoundException handled with error: {}", e.getMessage());
		return new ApiError(HttpStatus.UNAUTHORIZED.value(),  e.getMessage());
	}

	@ExceptionHandler(InternalAuthenticationServiceException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ApiError handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e) {
		log.error("InternalAuthenticationServiceException handled with error: {}", e.getMessage());
		return new ApiError(HttpStatus.UNAUTHORIZED.value(),  "The email address or password is incorrect!");
	}

	@ExceptionHandler(UserAlreadyRegisteredException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleUserAlreadyRegisteredException(UserAlreadyRegisteredException e) {
		log.error("UserAlreadyRegisteredException handled with error: {}", e.getMessage());
		return new ApiError(HttpStatus.BAD_REQUEST.value(),  e.getMessage());
	}

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleAllOtherException(RuntimeException e) {
        log.error("RuntimeException handled with error: {}", e.getMessage());
        return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
