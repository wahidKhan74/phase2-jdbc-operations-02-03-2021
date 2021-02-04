package com.ecommerce.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public Connection connection;
	
	// init method 
	public DBConnection (String url, String username, String password) throws SQLException, ClassNotFoundException {
		
		// 1. Register JDBC Driver Class
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// 2. Create connection
		this.connection = DriverManager.getConnection(url, username, password);
	}
	
	//get connection
	public Connection getConnection() {
		return this.connection;
	}
	
	// close connection
	public void closeConnection() throws SQLException   {
		if(this.connection !=null) {
			this.connection.close();
		}
	}
	

}
