package org.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

	public void create(User user) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();

		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		jdbcTemplate.executeUpdate(user, sql, pstmt -> {
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());
		});
	}

	// public User findByUserId(String userId) throws SQLException {
	// 	Connection con = null;
	// 	PreparedStatement pstst = null;
	// 	ResultSet rs = null;
	//
	// 	try {
	// 		con = ConnectionManager.getConnection();
	// 		String sql = "SELECT userId, password, name, email FROM users WHERE userId = ?";
	// 		pstst = con.prepareStatement(sql);
	// 		pstst.setString(1, userId);
	//
	// 		rs = pstst.executeQuery();
	//
	// 		User user = null;
	// 		if (rs.next()) {
	// 			user = new User(rs.getString("userId"),
	// 				rs.getString("password"),
	// 				rs.getString("name"),
	// 				rs.getString("email"));
	// 		}
	// 		return user;
	// 	} finally {
	// 		if (rs != null) {
	// 			rs.close();
	// 		}
	// 		if (pstst != null) {
	// 			pstst.close();
	// 		}
	// 		if (con != null) {
	// 			con.close();
	// 		}
	// 	}
	//
	// }
	public User findByUserId(String userId) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "SELECT userId, password, name, email FROM USERS WHERE userId = ?";
		return (User) jdbcTemplate.executeQuery(sql, pstst -> pstst.setString(1, userId),
		 resultSet -> new User(resultSet.getString("userId"), resultSet.getString("password"),
			resultSet.getString("name"), resultSet.getString("email")));
	}
}
