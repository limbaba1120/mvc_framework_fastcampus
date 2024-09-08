package org.example.jdbc;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class UserDaoTest {

	@BeforeEach
	void setUp() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("db_schema.sql"));
		DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
	}

	@Test
	void createTest() throws SQLException {
		UserDao userDao = new UserDao();
		User createUser = new User("wizard", "password", "name", "email");
		System.out.println(createUser);
		userDao.create(createUser);
		User user = userDao.findByUserId("wizard");
		assertThat(user).isEqualTo(new User("wizard", "password", "name", "email"));
	}
}
