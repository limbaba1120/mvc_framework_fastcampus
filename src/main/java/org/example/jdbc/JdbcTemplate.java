package org.example.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {

	public void executeUpdate(User user, String sql, PreparedStatementSetter pss) throws SQLException {
		Connection con = null;
		PreparedStatement pstst = null;

		try {
			con = ConnectionManager.getConnection();
			pstst = con.prepareStatement(sql);
			pss.setter(pstst);

			pstst.executeUpdate();

		} finally {
			if (pstst != null) {
				pstst.close();
			}
			if (con != null) {
				con.close();
			}

		}
	}

	public Object executeQuery(String sql, PreparedStatementSetter pss, RowMapper rowMapper) throws SQLException {
		Connection con = null;
		PreparedStatement pstst = null;
		ResultSet rs = null;

		try {
			con = ConnectionManager.getConnection();
			pstst = con.prepareStatement(sql);
			pss.setter(pstst);

			rs = pstst.executeQuery();

			Object obj = null;
			if (rs.next()) {
				return rowMapper.map(rs);
			}
			return obj;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstst != null) {
				pstst.close();
			}
			if (con != null) {
				con.close();
			}
		}

	}
}
