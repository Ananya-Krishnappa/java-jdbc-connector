/**
 * Purpose:JDBC connection
 * @author Ananya K
 * @version 1.0
 * @since 5/07/2021
 * 
 */
package com.bridgelabz.jdbcconnector;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bridgelabz.jdbcconnector.exception.JdbcConnectorException;

public class JdbcConnectionFactory {
	private static final Logger LOG = LogManager.getLogger(JdbcConnectionFactory.class);

	public static void main(String[] args) throws JdbcConnectorException {
		String jdbcUrl = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
		String username = "root";
		String password = "Ananyagowda123!";
		Connection connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			LOG.debug("Driver loaded");
		} catch (ClassNotFoundException e) {
			throw new JdbcConnectorException("Cannot find the driver in the class path" + e);
		}
		listDrivers();
		try {
			LOG.debug("Connecting to database" + jdbcUrl);
			connection = DriverManager.getConnection(jdbcUrl, username, password);
			LOG.debug("Connection is successful" + connection);
		} catch (Exception e) {
			throw new JdbcConnectorException(e.getMessage());
		}
	}

	/**
	 * Retrieves an Enumeration with all of the currently loaded JDBC drivers to
	 * which the current caller has access.
	 */
	private static void listDrivers() {
		Enumeration<Driver> driverList = DriverManager.getDrivers();
		while (driverList.hasMoreElements()) {
			Driver driverClass = driverList.nextElement();
			LOG.debug(" " + driverClass.getClass().getName());
		}
	}
}
