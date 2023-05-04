package ua.aleksenko.authorizationservice.model.exception;

public class UserAlreadyRegisteredException extends RuntimeException {

	private static final String ERROR_MESSAGE = "User with email = %s already registered!";

	public UserAlreadyRegisteredException(String email) {
		super(String.format(ERROR_MESSAGE, email));
	}
}
