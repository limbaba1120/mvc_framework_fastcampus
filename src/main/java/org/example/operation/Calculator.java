package org.example.operation;

import java.util.List;

import org.example.operation.calculate.AdditionOperator;
import org.example.operation.calculate.DivisionOperator;
import org.example.operation.calculate.MultiplicationOperator;
import org.example.operation.calculate.NewArithmeticOperator;
import org.example.operation.calculate.PositiveNumber;
import org.example.operation.calculate.SubtractionOperator;

public class Calculator {

	private static final List<NewArithmeticOperator> arithmeticOperators
		= List.of(new AdditionOperator(), new SubtractionOperator(), new MultiplicationOperator(),
		new DivisionOperator());

	public static int calculate(PositiveNumber operand1, String operator, PositiveNumber operand2) {
		return arithmeticOperators.stream()
			.filter(arithmeticOperators -> arithmeticOperators.supports(operator))
			.map(arithmeticOperators -> arithmeticOperators.calculate(operand1, operand2))
			.findFirst()
			.orElseThrow(() -> new ArithmeticException("No such operator: " + operator));
	}
}
