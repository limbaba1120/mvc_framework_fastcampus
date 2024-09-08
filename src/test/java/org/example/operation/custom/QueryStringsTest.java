package org.example.operation.custom;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QueryStringsTest {

	@Test
	void createTest() {
		QueryStrings queryStrings = new QueryStrings("operand1=11&operator=*&operand2=55"); // List<QueryString>
		assertThat(queryStrings).isNotNull();
	}
}