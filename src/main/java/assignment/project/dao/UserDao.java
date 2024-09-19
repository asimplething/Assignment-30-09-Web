package assignment.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import assignment.project.connection.DatabaseConnection;

public class UserDao {
	public static Connection dbConnection;

	public static CachedRowSet findUserByEmailAndPass(String Email, String Password) {

		try {
			//Initialize variables
			String sqlQueryString = "SELECT * FROM Users WHERE Email = ? AND Password = ?";
			CachedRowSet rowSet = RowSetProvider.newFactory().createCachedRowSet();
			dbConnection = DatabaseConnection.getConnection();
			//Prepare statement
			PreparedStatement statement = dbConnection.prepareStatement(sqlQueryString);
			//Set parameter
			statement.setString(1, Email);
			statement.setString(2, Password);
			statement.executeQuery();
			rowSet.populate(statement.getResultSet());
			//Close database connection and return result
			dbConnection.close();
			return rowSet;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}
	public static CachedRowSet findUserByEmail(String Email) {
		try {
			//Initialize variables
			String sqlQueryString = "SELECT * FROM Users WHERE Email = ?";
			CachedRowSet rowSet = RowSetProvider.newFactory().createCachedRowSet();
			dbConnection = DatabaseConnection.getConnection();
			//Prepare statement
			PreparedStatement statement = dbConnection.prepareStatement(sqlQueryString);
			//Set parameter
			statement.setString(1, Email);
			statement.executeQuery();
			rowSet.populate(statement.getResultSet());
			//Close database connection and return result
			dbConnection.close();
			return rowSet;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}
	public static int getTotalAccountsNumber() throws SQLException {
		dbConnection = DatabaseConnection.getConnection();
		try {
			PreparedStatement statement;
			statement = dbConnection.prepareStatement("SELECT count(*) FROM Users");
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			int resultNumber = resultSet.getInt(1);
			dbConnection.close();
			return resultNumber;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public static boolean insertAccount(String Name, String Email, String Password,String Created_Time) {
		dbConnection = DatabaseConnection.getConnection();
		try {
			PreparedStatement statement;
			statement = dbConnection.prepareStatement("INSERT INTO Users(UID, Name, Email, Password, Role, Created_Time) VALUES (?,?,?,?,?,?)");
			statement.setString(1, "UID" + UserDao.getTotalAccountsNumber());
			statement.setString(2, Name);
			statement.setString(3, Email);
			statement.setString(4, Password);
			statement.setBoolean(5, false);
			statement.setString(6, Created_Time);
			statement.executeUpdate();
			dbConnection.close();
			return true;	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
