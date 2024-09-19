package assignment.project.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

	public static Connection getConnection() {

		String connectionString = null;
		try {
			connectionString = "jdbc:sqlserver://"
					+ "localhost:1433;databaseName=UTESHOP;user=sa;password=123456789;encrypt=true;trustServerCertificate=true;";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			return DriverManager.getConnection(connectionString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
