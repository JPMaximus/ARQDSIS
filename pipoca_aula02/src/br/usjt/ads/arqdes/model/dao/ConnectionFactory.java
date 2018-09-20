package br.usjt.ads.arqdes.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// porque este erro nao pode ser prevenido via programa usa-se RuntimeException,
			// que é unchecked.
			throw new RuntimeException(e);
		}
	}

	public static Connection getConnection() throws IOException {
		try {;
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/pipocadb?"
					+ "user=root&password=102030&useSSL=false&useJDBCCompliantTimezoneShift=true&"
					+ "useLegacyDatetimeCode=false&serverTimezone=UTC");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
	}

}
