package ru.innopolis.refrigerator.core.db.jdbc.dao.cookingethod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.jdbc.connection.postgresql.ConnectionFactoryPostgreSQL;
import ru.innopolis.refrigerator.core.db.jdbc.connection.IConnectionFactory;
import ru.innopolis.refrigerator.core.db.jdbc.exception.CookingMethodDAOException;
import ru.innopolis.refrigerator.core.model.cookingmethod.CookingMethod;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CookingMethodDAO {

	private static final Logger logger = LogManager.getLogger(CookingMethodDAO.class.getName());
	private static IConnectionFactory connection;

	static {
		connection = ConnectionFactoryPostgreSQL.getInstance();
	}

	public static List<CookingMethod> getAll() throws CookingMethodDAOException {
		List<CookingMethod> cookingMethodList = new ArrayList<>();

		try {
			Statement statement = connection.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM CookingMethod");

			while (resultSet.next()) {
				CookingMethod cookingMethod = new CookingMethod(resultSet.getString("name"));
				cookingMethodList.add(cookingMethod);
			}
		} catch (SQLException e) {
			logger.error("I can not get of all items to the database" + e.toString());
			throw new CookingMethodDAOException();
		}
		return cookingMethodList;
	}
}