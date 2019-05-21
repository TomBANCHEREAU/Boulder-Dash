package com.TomBAN.mySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {
	private static MySQL MYSQL;
	private Connection connection;
	private Statement statement;

	private MySQL(final String url, final String user, final String password) throws SQLException{


		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ResultSet querySelect(final String request) {
		try {
			return statement.executeQuery(request);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public int queryUpdate(final String request) {
		try {
			return statement.executeUpdate(request);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	public static void Connect(final String url, final String user, final String password) throws SQLException{
		if (MYSQL != null) {
			closeConnection();
		}
		MYSQL = new MySQL(url, user, password);
	}

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

	public static MySQL getInstance() {
		return MYSQL;
	}
}
