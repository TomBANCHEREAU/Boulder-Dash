package com.TomBAN.mySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class permit a basic access to mySQL using the cj.jdbc Driver
 * @author TomBANCHEREAU
 */
public class MySQL {
	private static MySQL MYSQL;
	private Connection connection;
	private Statement statement;

	private MySQL(final String url, final String user, final String password) throws SQLException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Do a query on a mySQL server
	 * @param request
	 * the query (Exemple:"SELECT * FROM 'tableName'")
	 * @return
	 * The ResultSet of the query
	 */
	public ResultSet querySelect(final String request) {
		try {
			statement = connection.createStatement();
			return statement.executeQuery(request);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Do a update type query on a mySQL server
	 * @param request
	 * @return
	 */
	public int queryUpdate(final String request) {
		try {
			statement = connection.createStatement();
			return statement.executeUpdate(request);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	/**
	 * Connect to a mySQL server (and close the previous connection)
	 * @param url
	 * url of the server
	 * @param user
	 * your username
	 * @param password
	 * your password
	 * @throws SQLException
	 */
	public static void Connect(final String url, final String user, final String password) throws SQLException{
		if (MYSQL != null) {
			closeConnection();
		}
		MYSQL = new MySQL(url, user, password);
	}

	/**
	 * Close the current connection
	 */
	public static void closeConnection() {
		if(MYSQL!=null) {
			try {
				MYSQL.connection.close();
				MYSQL.statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			MYSQL = null;
		}
	}

	/**
	 * return an instance of this class to do queries
	 * @return
	 */
	public static MySQL getInstance() {
		return MYSQL;
	}
}
