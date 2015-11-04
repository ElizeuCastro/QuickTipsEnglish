package br.com.quicktipsenglish.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionFactory {

	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";
	private static final String PATH_DB = "jdbc:mysql://localhost/quicktipsenglish";
	private static Connection connection;

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(PATH_DB, DB_USER,
					DB_PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		return connection;
	}

	public static void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
