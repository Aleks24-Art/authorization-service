package ua.aleksenko.authorizationservice.model.exception;

public class UserNotFoundException extends RuntimeException {

	private static final String ERROR_MESSAGE = "User with email = %s does not exist";

	public UserNotFoundException(String email) {
		super(String.format(ERROR_MESSAGE, email));
	}
}
