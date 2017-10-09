package ru.innopolis.refrigerator.core.db.jdbc.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.jdbc.connection.ConnectionFactoryPostgreSQL;
import ru.innopolis.refrigerator.core.db.jdbc.connection.IConnectionFactory;
import ru.innopolis.refrigerator.core.db.jdbc.exception.SessionDAOException;
import ru.innopolis.refrigerator.core.db.jdbc.exception.UserDAOException;
import ru.innopolis.refrigerator.core.model.Session;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SessionDAO {

	private static final Logger logger = LogManager.getLogger(SessionDAO.class.getName());
	private static IConnectionFactory connection;

	static {
		connection = ConnectionFactoryPostgreSQL.getInstance();
	}

	public static List<Session> getAll() throws SessionDAOException {
		List<Session> sessions = new ArrayList<>();

		try {
			Statement statement = connection.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Session");

			while(resultSet.next()) {
				Session session = new Session(resultSet.getString("sessionId"),
						UserDAO.getById(resultSet.getLong("user_id")),
						resultSet.getString("session_user_agent"),
						resultSet.getDate("session_finish_time"),
						resultSet.getDate("session_start_time"),
						resultSet.getBoolean("remember"));
				sessions.add(session);
			}
		}
		catch (UserDAOException e) {
			logger.error("I can not get of all items to the database" + e.toString());
		}
		catch (SQLException e) {
			logger.error("I can not get of all items to the database" + e.toString());
			throw new SessionDAOException();
		}

		return sessions;
	}
}
