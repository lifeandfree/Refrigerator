package ru.innopolis.refrigerator.core.db.jdbc.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.jdbc.connection.ConnectionFactoryPostgreSQL;
import ru.innopolis.refrigerator.core.db.jdbc.connection.IConnectionFactory;
import ru.innopolis.refrigerator.core.db.jdbc.exception.UserDAOException;
import ru.innopolis.refrigerator.core.model.Role;
import ru.innopolis.refrigerator.core.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

	private static IConnectionFactory connection;
	private static final Logger logger = LogManager.getLogger(UserDAO.class.getName());

	static {
		connection = ConnectionFactoryPostgreSQL.getInstance();
	}

	public static List<User> getAll() throws UserDAOException {
		List<User> users = new ArrayList<>();

		try {
			Statement statement = connection.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM User");

			while(resultSet.next()) {
				User user = new User(resultSet.getString("username"), resultSet.getString("password"), Role.valueOf(resultSet.getString("role")), resultSet.getString("email"));
				users.add(user);
			}
		}
		catch (SQLException e) {
			logger.error("I can not get of all items to the database" + e.toString());
			throw new UserDAOException();
		}
		return users;
	}

	public static User getById(long id) throws UserDAOException {
		User user = new User();

		try {
			Statement statement = connection.getConnection().createStatement();

			ResultSet resultSet = statement.executeQuery("SELECT * FROM \"User\" where id=" + id);

			if (resultSet.next()) {
				user = new User(resultSet.getString("username"), resultSet.getString("password"), Role.valueOf(resultSet.getString("role")), resultSet.getString("email"));

			}
		} catch (SQLException e) {
			logger.error("I can not get an item to the database" + e.toString());
			throw new UserDAOException();
		}
		return user;
	}
}