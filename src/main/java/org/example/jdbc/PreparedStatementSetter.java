package org.example.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementSetter {
	void setter(PreparedStatement pstst) throws SQLException;
}
