package org.example.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper {
	Object map(ResultSet resultSet) throws SQLException;

}
