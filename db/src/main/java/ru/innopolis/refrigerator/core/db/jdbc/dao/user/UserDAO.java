package ru.innopolis.refrigerator.core.db.jdbc.dao.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.jdbc.connection.postgresql.ConnectionFactoryPostgreSQL;
import ru.innopolis.refrigerator.core.db.jdbc.connection.ConnectionFactory;
import ru.innopolis.refrigerator.core.db.jdbc.exception.UserDAOException;
import ru.innopolis.refrigerator.core.model.enumcls.Role;
import ru.innopolis.refrigerator.core.model.user.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

	private static ConnectionFactory connection;
	private static final Logger logger = LogManager.getLogger(UserDAO.class.getName());

	static {
		connection = ConnectionFactoryPostgreSQL.getInstance();
	}

	public List<User> getAll() throws UserDAOException {
		List<User> users = new ArrayList<>();

		try {
			Statement statement = connection.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM \"User\"");

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

	public User getById(long id) throws UserDAOException {
		User user = new User();

		try {
			Statement statement = connection.getConnection().createStatement();

			ResultSet resultSet = statement.executeQuery("SELECT * FROM \"User\" where id=" + id);

			if (resultSet.next()) {
				user = new User(resultSet.getString("username"), resultSet.getString("password"), Role.valueOf(resultSet.getString("role")), resultSet.getString("email"));

			}
		}
		catch (SQLException e) {
			logger.error("I can not get an item to the database" + e.toString());
			throw new UserDAOException();
		}
		return user;
	}

	public void insertOne(User user) throws UserDAOException {
		String sql = "INSERT INTO \"User\" (username, password, role, email) VALUES(?,?,?,?)";
		try {
			PreparedStatement ps = connection.getConnection().prepareStatement(sql);
			ps.clearParameters();
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getRole().toString());
			ps.setString(4, user.getEmail());
			ps.executeBatch();
		}
		catch (SQLException e) {
			logger.error("I can not add an item to the database" + e.toString());
			throw new UserDAOException();
		}
	}

	public void insertAll(List<User> users) throws UserDAOException {
		String sql = "INSERT INTO \"User\" (username, password, role, email) VALUES(?,?,?,?)";
		try {
			PreparedStatement ps = connection.getConnection().prepareStatement(sql);
			for (User user : users) {
				ps.clearParameters();
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getRole().toString());
				ps.setString(4, user.getEmail());
				ps.addBatch();
			}
			ps.executeBatch();
		}
		catch (SQLException e) {
			logger.error("I can not add an item to the database" + e.toString());
			throw new UserDAOException();
		}
	}

	public long getId(User user) throws UserDAOException {
		long userId = 0;
		try {
			PreparedStatement statement = connection.getConnection().prepareStatement("SELECT * FROM \"User\" WHERE username=? AND email=?");
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getEmail());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				userId = resultSet.getLong("id");
			}
		}
		catch (SQLException e) {
			logger.error("I can not get an item to the database" + e.toString());
			throw new UserDAOException();
		}
		return userId;
	}

	public Boolean createUser(User user) {
		try {
			insertOne(user);
			return true;
		}
		catch (UserDAOException e) {
			logger.error("I can not get an item to the database" + e.toString());
			return false;
		}
	}
}