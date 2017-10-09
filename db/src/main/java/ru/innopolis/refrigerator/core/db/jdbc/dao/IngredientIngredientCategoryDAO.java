package ru.innopolis.refrigerator.core.db.jdbc.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.jdbc.connection.ConnectionFactoryPostgreSQL;
import ru.innopolis.refrigerator.core.db.jdbc.connection.IConnectionFactory;
import ru.innopolis.refrigerator.core.db.jdbc.exception.IngredientIngredientCategoryDAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientIngredientCategoryDAO {

	private static final Logger logger = LogManager.getLogger(SessionDAO.class.getName());
	private static IConnectionFactory connection;

	static {
		connection = ConnectionFactoryPostgreSQL.getInstance();
	}

	public static List<Long> getAllByIngredientId(long id) throws IngredientIngredientCategoryDAOException {
	ArrayList<Long> ingToIngCategs = new ArrayList<>();

		try {
			PreparedStatement statement = connection.getConnection().prepareStatement("SELECT * FROM Ingredient_ingredientCategory where id=?");
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Long ingToIngCateg = resultSet.getLong("ingredientCategory_id");

			}
		} catch (SQLException e) {
			logger.error("I can not get an item to the database" + e.toString());
			throw new IngredientIngredientCategoryDAOException();
		}
		return ingToIngCategs;
	}
}