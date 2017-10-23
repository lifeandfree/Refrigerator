package ru.innopolis.refrigerator.core.db.jdbc.dao.session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.jdbc.connection.postgresql.ConnectionFactoryPostgreSQL;
import ru.innopolis.refrigerator.core.db.jdbc.connection.ConnectionFactory;
import ru.innopolis.refrigerator.core.db.jdbc.dao.DaoFactory;
import ru.innopolis.refrigerator.core.db.jdbc.dao.user.UserDAO;
import ru.innopolis.refrigerator.core.db.jdbc.exception.SessionDAOException;
import ru.innopolis.refrigerator.core.db.jdbc.exception.UserDAOException;
import ru.innopolis.refrigerator.core.model.enumcls.Role;
import ru.innopolis.refrigerator.core.model.session.Session;
import ru.innopolis.refrigerator.core.model.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessionDAO {

	private static final Logger logger = LogManager.getLogger(SessionDAO.class.getName());
	private static ConnectionFactory connection;

	static {
		connection = ConnectionFactoryPostgreSQL.getInstance();
	}

	public List<Session> getAll() throws SessionDAOException {
		List<Session> sessions = new ArrayList<>();

		try {
			Statement statement = connection.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM \"Session\" s JOIN \"User\" u ON s.user_id=u.id");

			while(resultSet.next()) {
				User user = new User(resultSet.getString("username"), resultSet.getString("password"), Role.valueOf(resultSet.getString("role")), resultSet.getString("email"));
				Session session = new Session(resultSet.getString("role"), user,
						resultSet.getString("session_user_agent"),
						resultSet.getLong("session_finish_time"),
						resultSet.getLong("session_start_time"),
						resultSet.getBoolean("remember"));
				sessions.add(session);
			}
		}
		catch (SQLException e) {
			String msg = "I can not get of all items to the database" + e.toString();
			logger.error(msg);
			throw new SessionDAOException(msg);
		}

		return sessions;
	}

	public void insertAll(List<Session> sessions) throws SessionDAOException {
		String sql = "INSERT INTO \"Session\" (remember, sessionid, session_finish_time, session_start_time, session_user_agent, user_id) VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = connection.getConnection().prepareStatement(sql);
			for (Session session : sessions) {
				long id = 0;
				try {
					id = DaoFactory.getInstance().getUserDAO().getId(session.getUser());
				}
				catch (UserDAOException e) {
					String msg = "I can not get an user to the database" + e.toString();
					logger.error(msg);
					throw new SessionDAOException(msg);
				}
				if (id >0){
					ps.clearParameters();
					ps.setBoolean(1, (Boolean) session.isRemember());
					ps.setString(2, session.getSessionId());
					ps.setLong(3, Long.valueOf(session.getSession_finish_time()));
					ps.setLong(4,  Long.valueOf(session.getSession_start_time()));
					ps.setString(5, session.getSession_user_agent());
					ps.setLong(6, id);
					ps.addBatch();
				}
				else {
					String msg = "I can not add an session to the database. Not User" + session.getUser().toString();
					logger.error(msg);
					throw new SessionDAOException(msg);
				}
			}
			ps.executeBatch();
		}
		catch (SQLException e) {
			String msg = "I can not add an item to the database" + e.toString();
			logger.error(msg);
			throw new SessionDAOException(msg);
		}
	}

	public Session insertOne(Session session) throws SessionDAOException {
		String sql = "INSERT INTO \"Session\" (remember, sessionid, session_finish_time, session_start_time, session_user_agent, user_id) VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = connection.getConnection().prepareStatement(sql);
				long id = 0;
				try {
					id = DaoFactory.getInstance().getUserDAO().getId(session.getUser());
				}
				catch (UserDAOException e) {
					String msg = "I can not get an user to the database" + e.toString();
					logger.error(msg);
					throw new SessionDAOException(msg);
				}
				if (id >0){
					ps.clearParameters();
					ps.setBoolean(1, (Boolean) session.isRemember());
					ps.setString(2, session.getSessionId());
					ps.setLong(3, Long.valueOf(session.getSession_finish_time()));
					ps.setLong(4,  Long.valueOf(session.getSession_start_time()));
					ps.setString(5, session.getSession_user_agent());
					ps.setLong(6, id);
					ps.addBatch();
				}
				else {
					String msg = "I can not add an session to the database. Not User" + session.getUser().toString();
					logger.error(msg);
					throw new SessionDAOException(msg);
				}
			ps.executeBatch();
		}
		catch (SQLException e) {
			String msg = "I can not add an item to the database" + e.toString();
			logger.error(msg);
			throw new SessionDAOException(msg);
		}
		return session;
	}

	public long findSession(String sid, long uid) throws SessionDAOException {
		long sessionId = 0;
		try {
			PreparedStatement statement = connection.getConnection().prepareStatement("SELECT * FROM \"Session\" WHERE sessionid=? AND user_id=?");
			statement.setString(1, sid);
			statement.setLong(2, uid);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				sessionId = resultSet.getLong("id");
			}
		}
		catch (SQLException e) {
			String msg = "I can not get an item to the database" + e.toString();
			logger.error(msg);
			throw new SessionDAOException(msg);
		}
		return sessionId;
	}

	public boolean deleteById(long sessionId) throws SessionDAOException {
		try {
			Statement statement = connection.getConnection().createStatement();
			statement.executeUpdate("DELETE \"Session\" WHERE id = "+sessionId);
		}
		catch (SQLException e) {
			String msg = "I can not delete an item to the database" + e.toString();
			logger.error(msg);
			throw new SessionDAOException(msg);
		}
		return true;

	}

	public Session getSessionBySidAndUid(String sid, long uid) throws SessionDAOException {
		Session session = null;
		try {
			PreparedStatement statement = connection.getConnection().prepareStatement("SELECT * FROM \"Session\" s JOIN \"User\" u ON s.user_id=u.id WHERE s.sessionid=? AND s.user_id=?");
			statement.setString(1, sid);
			statement.setLong(2, uid);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				User user = new User(resultSet.getString("username"), resultSet.getString("password"), Role.valueOf(resultSet.getString("role")), resultSet.getString("email"));
				session = new Session(resultSet.getString("role"), user,
						resultSet.getString("session_user_agent"),
						resultSet.getLong("session_finish_time"),
						resultSet.getLong("session_start_time"),
						resultSet.getBoolean("remember"));
			}
		}
		catch (SQLException e) {
			String msg = "I can not get an item to the database" + e.toString();
			logger.error(msg);
			throw new SessionDAOException(msg);
		}
		return session;
	}
}
