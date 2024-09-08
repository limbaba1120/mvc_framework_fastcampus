package org.example.password;

public class PasswordValidator {

	public static final String PASSWORD_LENGTH_MUST_BE_BETWEEN_8_AND_12 = "Password length must be between 8 and 12";

	public static void validate(String password) {
		int length = password.length();

		if (length < 8 || length > 12) {
			throw new IllegalArgumentException(PASSWORD_LENGTH_MUST_BE_BETWEEN_8_AND_12);
		}
	}
}
