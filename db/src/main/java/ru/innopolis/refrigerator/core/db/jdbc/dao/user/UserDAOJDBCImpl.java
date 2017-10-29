package ru.innopolis.refrigerator.core.db.jdbc.dao.user;

import org.springframework.stereotype.Component;
import ru.innopolis.refrigerator.core.db.dao.user.UserDAO;
import ru.innopolis.refrigerator.core.db.jdbc.connection.postgresql.ConnectionFactoryPostgreSQL;
import ru.innopolis.refrigerator.core.db.jdbc.connection.ConnectionFactory;
import ru.innopolis.refrigerator.core.db.DaoFactory;
import ru.innopolis.refrigerator.core.db.exception.UserDAOException;
import ru.innopolis.refrigerator.core.logger.LogHandler;
import ru.innopolis.refrigerator.core.model.enumcls.Role;
import ru.innopolis.refrigerator.core.model.user.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDAOJDBCImpl implements UserDAO<User>{

	private static ConnectionFactory connection;
	private LogHandler logger = new LogHandler();

	static {
		connection = ConnectionFactoryPostgreSQL.getInstance();
	}

	@Override
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


	@Override
	public User update(User el) throws UserDAOException {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new UserDAOException(msg);
	}

	@Override
	public User getById(Long id) throws UserDAOException {
		User user = new User();

		try {
			Statement statement = connection.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM \"User\" where id=" + id);
			if (resultSet.next()) {
				user = new User(resultSet.getString("username"),
						resultSet.getString("password"),
						Role.valueOf(resultSet.getString("role")),
						resultSet.getString("email"), id);
			}
		}
		catch (SQLException e) {
			logger.error("I can not get an item to the database" + e.toString());
			throw new UserDAOException();
		}
		return user;
	}

	@Override
	public User add(User user) throws UserDAOException {
		String sql = "INSERT INTO \"User\" (username, password, role, email) VALUES(?,?,?,?)";
		try {
			PreparedStatement ps = connection.getConnection().prepareStatement(sql);
			ps.clearParameters();
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getRole().toString());
			ps.setString(4, user.getEmail());
			ps.addBatch();
			ps.executeBatch();
		}
		catch (SQLException e) {
			logger.error("I can not add an item to the database" + e.toString());
			throw new UserDAOException();
		}
		return user;
	}

	@Override
	public User delete(User el) throws Exception {
		String msg = "This method is not implemented";
		logger.error(msg);
		throw new UserDAOException(msg);
	}

	@Override
	public Collection<User> addAll(Collection<User> users) throws UserDAOException {
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
		return users;
	}

	@Override
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

	@Override
	public Boolean createUser(User user) {
		try {
			add(user);
			return true;
		}
		catch (UserDAOException e) {
			logger.error("I can not get an item to the database" + e.toString());
			return false;
		}
	}

	@Override
	public boolean checkDublicateUser(String login, String email) throws UserDAOException {
		logger.info("I check duplicate user to the database");
		User user = new User();
		user.setEmail(email);
		user.setUsername(login);
		long userid = DaoFactory.getInstance().getUserDAO().getId(user);
		if (userid > 0) {
			return true;
		}
		return false;
	}

	@Override
	public User getUserByLoginAndPassword(String login, String psw) throws UserDAOException {
		User user = new User();

		try {
			PreparedStatement statement = connection.getConnection().prepareStatement("SELECT * FROM \"User\" WHERE username=? AND password=?");
			statement.setString(1, login);
			statement.setString(2, psw);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				user = new User(resultSet.getString("username"), resultSet.getString(""), Role.valueOf(resultSet.getString("role")), resultSet.getString("email"));

			}
		}
		catch (SQLException e) {
			String msg = "I can not get an item to the database" + e.toString();
			logger.error(msg);
			throw new UserDAOException(msg);
		}
		return user;
	}

	@Override
	public long getUserIdByLoginAndPassword(String login, String psw) throws UserDAOException {
		long userId = 0;

		try {
			PreparedStatement statement = connection.getConnection().prepareStatement("SELECT * FROM \"User\" WHERE username=? AND password=?");
			statement.setString(1, login);
			statement.setString(2, psw);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
					userId = resultSet.getLong("id");
			}
		}
		catch (SQLException e) {
			String msg = "I can not get an item to the database" + e.toString();
			logger.error(msg);
			throw new UserDAOException(msg);
		}
		return userId;
	}
}