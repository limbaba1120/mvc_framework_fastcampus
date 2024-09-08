package org.example.operation;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

import java.util.stream.Stream;

import org.example.operation.calculate.PositiveNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CalculatorTest {


	// 1 + 2 -----> Calculator 전달
	//   3    <-----     (결과값 전달)
 	@DisplayName("덧셈, 뺄셈, 곱셈, 나눗셈 연산을 수행한다.")
	@ParameterizedTest
	@MethodSource("formulaAndResult")
	void calculateTest(int operand1, String operator, int operand2, int expectedResult) {

		int calculateResult = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));

		assertThat(calculateResult).isEqualTo(expectedResult);
	}

	private static Stream<Arguments> formulaAndResult() {
		return Stream.of(
			arguments(1, "+", 2, 3),
			arguments(1, "-", 2, -1),
			arguments(4, "*", 2, 8),
			arguments(4, "/", 2, 2)
		);
	}

}
