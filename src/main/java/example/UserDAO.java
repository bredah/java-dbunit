package example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple USER DAO
 * 
 * @since 20170224
 * @version 1.0
 * @author henriquebreda
 */
public class UserDAO {

	private static Connection connection;
	private static List<User> userList;

	/**
	 * Retrieve list with users registered in database
	 * 
	 * @return List with users
	 * @throws SQLException
	 *             Failed to close connection
	 */
	public static List<User> listUsers() throws SQLException {

		connection = new ConnectionFactory().getConnection();
		ResultSet rs = null;
		String query = "SELECT login, name, email FROM user;";
		try {
			PreparedStatement stm = connection.prepareStatement(query);
			userList = new ArrayList<>();
			rs = stm.executeQuery();
			while (rs.next()) {
				User usuario = new User();
				usuario.setLogin(rs.getString("login"));
				usuario.setName(rs.getString("name"));
				usuario.setEmail(rs.getString("email"));
				userList.add(usuario);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Unable to recover users from database", e);
		} finally {
			rs.close();
			connection.close();
		}
		return userList;
	}

	/**
	 * Add a new user in database
	 * 
	 * @param user
	 *            A new User
	 * @throws SQLException
	 *             Failed to close connection
	 */
	public static void addUser(User user) throws SQLException {
		connection = new ConnectionFactory().getConnection();
		PreparedStatement stm = null;
		String query = "INSERT INTO user (login, name, email) VALUES (?, ?, ?);";
		try {
			stm = connection.prepareStatement(query);
			stm.setString(1, user.getLogin());
			stm.setString(2, user.getName());
			stm.setString(3, user.getEmail());
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Unable to add a new user", e);
		} finally {
			stm.close();
			connection.close();
		}
	}
}
