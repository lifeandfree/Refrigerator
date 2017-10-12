package ru.innopolis.refrigerator.core.db.jdbc.dao.session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.jdbc.connection.postgresql.ConnectionFactoryPostgreSQL;
import ru.innopolis.refrigerator.core.db.jdbc.connection.IConnectionFactory;
import ru.innopolis.refrigerator.core.db.jdbc.exception.SessionDAOException;
import ru.innopolis.refrigerator.core.model.enumcls.Role;
import ru.innopolis.refrigerator.core.model.session.Session;
import ru.innopolis.refrigerator.core.model.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SessionDAO {
	public static void main(String[] args) {
		try {
			getAll();
		}
		catch (SessionDAOException e) {
			e.printStackTrace();
		}
	}

	private static final Logger logger = LogManager.getLogger(SessionDAO.class.getName());
	private static IConnectionFactory connection;

	static {
		connection = ConnectionFactoryPostgreSQL.getInstance();
	}

	public static List<Session> getAll() throws SessionDAOException {
		List<Session> sessions = new ArrayList<>();

		try {
			Statement statement = connection.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM \"Session\" s JOIN \"User\" u ON s.user_id=u.id;");

			while(resultSet.next()) {
				logger.info(resultSet.getString("role"));
				User user = new User(resultSet.getString("username"), resultSet.getString("password"), Role.valueOf(resultSet.getString("role")), resultSet.getString("email"));
				Session session = new Session(resultSet.getString("role"), user,
						resultSet.getString("session_user_agent"),
						resultSet.getTimestamp("session_finish_time"),
						resultSet.getTimestamp("session_start_time"),
						resultSet.getBoolean("remember"));
				sessions.add(session);
				System.out.println(resultSet.getString("id"));
				System.out.println(session);
			}
		}
		catch (SQLException e) {
			logger.error("I can not get of all items to the database" + e.toString());
			throw new SessionDAOException();
		}

		return sessions;
	}
}