package br.com.start.h2memory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.start.entitys.User;

public class H2MemoryDatabase {

	private static final String DB_DRIVER = "org.h2.Driver";
	private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
	private static final String DB_USER = "";
	private static final String DB_PASSWORD = "";
	
	public static void connect()  {
		
		Connection connection = getDBConnection();
		try {
			connection.setAutoCommit(false);
			createTable(connection);
			insertWithStatement(connection);
			ArrayList<User> users = select(connection);
			dropTable(connection);
			connection.close();
			System.out.println(users);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

	public static void createTable(Connection connection) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		stmt.execute(
				"CREATE TABLE USER(id long primary key, name varchar(255), email varchar(50), password varchar(100))");
		stmt.close();
		connection.commit();
	}

	private static void insertWithStatement(Connection connection) throws SQLException {

		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			stmt.execute(
					"INSERT INTO USER(id, name, email, password) VALUES (1, 'Anju', 'Anjuslove@gmail.com', '12345')");
			stmt.execute(
					"INSERT INTO USER(id, name, email, password) VALUES (2, 'Sonia','Sonialove@gmail.com','54321')");
			stmt.execute(
					"INSERT INTO USER(id, name, email, password) VALUES (3, 'Asha', 'Ashaslove@gmail.com', '76237')");
			System.out.println("Dados dos Usuarios");
			stmt.close();
			connection.commit();
		} catch (SQLException e) {
			System.out.println("Exception Message " + e.getLocalizedMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}

	private static ArrayList<User> select(Connection connection) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("select * from USER");
		connection.commit();
		ArrayList<User> users = new ArrayList<User>();
		while (rs.next()) {
			User user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			users.add(user);
		}
		stmt.close();
		return users;
	}
	
	private static void dropTable(Connection connection)throws SQLException{
		Statement stmt = null;
		stmt = connection.createStatement();
		stmt.execute("DROP TABLE USER");
		connection.commit();
		stmt.close();


	}
		
	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}

}
