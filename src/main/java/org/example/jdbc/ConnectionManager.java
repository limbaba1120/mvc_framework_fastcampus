package org.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

public class ConnectionManager {

	public static final String DB_DRIVER = "org.h2.Driver";
	public static final String DB_URL_PATH = "jdbc:h2:mem://localhost/~/jdbc-practice;MODE=MySQL;DB_CLOSE_DELAY=-1";
	public static final String DB_USERNAME = "sa";
	public static final String DB_PASSWORD = "";
	public static final int MAX_POOL_SIZE = 40;
	public static final int MIN_IDLE = 5;

	private static final DataSource ds;

	static {
		HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setDriverClassName(DB_DRIVER);
		hikariDataSource.setJdbcUrl(DB_URL_PATH);
		hikariDataSource.setUsername(DB_USERNAME);
		hikariDataSource.setPassword(DB_PASSWORD);
		hikariDataSource.setMaximumPoolSize(MAX_POOL_SIZE);
		hikariDataSource.setMinimumIdle(MIN_IDLE);

		ds = hikariDataSource;
	}

	public static Connection getConnection() throws SQLException {
		try {
			return ds.getConnection();

		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	public static DataSource getDataSource() {
		return ds;
	}


}
