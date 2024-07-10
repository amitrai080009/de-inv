package com.upmscl.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class GetConnection {

	// method for create connection from jdbc properties file.
	public Connection getConnection() throws Exception {

		Properties properties = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
			// Handle exception
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		String jdbcUrl = properties.getProperty("jdbc.url");
		String username = properties.getProperty("jdbc.username");
		String password = properties.getProperty("jdbc.password");

		Class.forName("com.mysql.cj.jdbc.Driver");
		
		return DriverManager.getConnection(jdbcUrl, username, password);
	}
}
