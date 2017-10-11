package ru.innopolis.refrigerator.core.db.jdbc.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.jdbc.connection.ConnectionFactoryPostgreSQL;
import ru.innopolis.refrigerator.core.db.jdbc.connection.IConnectionFactory;
import ru.innopolis.refrigerator.core.db.jdbc.exception.RefrigeratorDAOException;
import ru.innopolis.refrigerator.core.db.jdbc.exception.RefrigeratorIngredientDAOException;
import ru.innopolis.refrigerator.core.db.jdbc.exception.UserDAOException;
import ru.innopolis.refrigerator.core.model.Refrigerator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RefrigeratorDAO {

	private static final Logger logger = LogManager.getLogger(RefrigeratorDAO.class.getName());
	private static IConnectionFactory connection;

	static {
		connection = ConnectionFactoryPostgreSQL.getInstance();
	}

	public static List<Refrigerator> getAll() throws RefrigeratorDAOException {
		List<Refrigerator> refrigerators = new ArrayList<>();

		try {
			Statement statement = connection.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Refrigerator");

			while(resultSet.next()) {
				Refrigerator refrigerator = new Refrigerator(
						resultSet.getString("name"),
						UserDAO.getById(resultSet.getLong("user_id")),
						RefrigeratorIngredientDAO.getAllByRefrigeratorId(resultSet.getLong("id"))
				);
				refrigerators.add(refrigerator);
			}
		}
		catch (UserDAOException e) {
			logger.error("I can not get of all items to the database" + e.toString());
		}
		catch (RefrigeratorIngredientDAOException e) {
			logger.error("I can not get of all items to the database" + e.toString());
		}
		catch (SQLException e) {
			logger.error("I can not get of all items to the database" + e.toString());
			throw new RefrigeratorDAOException();
		}

		return refrigerators;
	}
}