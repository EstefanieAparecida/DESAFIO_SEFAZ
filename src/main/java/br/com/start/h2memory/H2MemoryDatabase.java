package br.com.start.h2memory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class H2MemoryDatabase {

	private static final String DB_DRIVER = "org.h2.Driver";
	private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
	private static final String DB_USER = "";
	private static final String DB_PASSWORD = "";

	public static void connect() {
		Connection connection = getDBConnection();
		try {
			connection.setAutoCommit(false);
			createTable(connection);
			insertWithStatement(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createTable(Connection connection) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		stmt.execute("CREATE TABLE PERSON(id int primary key, name varchar(255), email varchar(50), senha varchar(100))");
		stmt.close();
		connection.commit();
	}

	private static void insertWithStatement(Connection connection) throws SQLException {

		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			stmt.execute("INSERT INTO PERSON(id, name, email, senha) VALUES (1, 'Anju', 'Anjuslove@gmail.com', '12345')");
			stmt.execute("INSERT INTO PERSON(id, name, email, senha) VALUES (2, 'Sonia','Sonialove@gmail.com','54321')");
			stmt.execute("INSERT INTO PERSON(id, name, email, senha) VALUES (3, 'Asha', 'Ashaslove@gmail.com', '76237')");
			ResultSet rs = stmt.executeQuery("select * from PERSON");
			System.out.println("Dados da Tabela");
			while (rs.next()) {
				System.out.println("ID:"+ rs.getString("id")+ " Nome:" + rs.getString("name") + " Email:" + rs.getString("email") + " Senha:"
						+ rs.getString("senha"));
			}
			stmt.execute("DROP TABLE PERSON");
			stmt.close();
			connection.commit();
		} catch (SQLException e) {
			System.out.println("Exception Message " + e.getLocalizedMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
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
