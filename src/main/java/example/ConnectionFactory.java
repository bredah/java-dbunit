package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exemple.core.DbProperties;
import exemple.core.Utils;

/**
 * Connection factory
 * 
 * @since 20170224
 * @version 1.0
 * @author henriquebreda
 */
public class ConnectionFactory {

	/**
	 * Create a connection to database
	 * 
	 * @return Database connection
	 */
	public Connection getConnection() {
		try {
			return DriverManager.getConnection(
					Utils.getDbProperty(DbProperties.CONNECTION_URL),
					Utils.getDbProperty(DbProperties.USERNAME), 
					Utils.getDbProperty(DbProperties.PASSWORD));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
