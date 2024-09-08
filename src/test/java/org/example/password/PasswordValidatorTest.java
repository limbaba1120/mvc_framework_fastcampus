package org.example.password;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PasswordValidatorTest {

	@DisplayName("비밀번호가 최소 8자 이상, 12자 이하면 예외가 발생하지 않는다.")
	@Test
	void validatePasswordTest() {
		assertThatCode(() -> PasswordValidator.validate("limbaba1120"))
			.doesNotThrowAnyException();
	}

	@DisplayName("비밀번호가 8자 미만 또는 12자 초과하는 경우 IllegalArgumentException 예외가 발생한다.")
	@ParameterizedTest
	@ValueSource(strings = {"aabbccd", "aabbccddeeffh"})
	void validatePasswordTest2(String password) {
		assertThatCode(() -> PasswordValidator.validate(password))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("Password length must be between 8 and 12");
	}
}
