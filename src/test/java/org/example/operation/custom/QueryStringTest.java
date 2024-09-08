package org.example.operation.custom;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class QueryStringTest {

	// List<QueryString>
	@Test
	void createTest() {
		QueryString queryString = new QueryString("operand1", "11");

		assertThat(queryString).isNotNull();
	}
}
