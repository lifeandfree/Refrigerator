package ru.innopolis.refrigerator.core.db.jdbc.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.jdbc.connection.ConnectionFactoryPostgreSQL;
import ru.innopolis.refrigerator.core.db.jdbc.connection.IConnectionFactory;
import ru.innopolis.refrigerator.core.db.jdbc.exception.IngredientCategoryDAOException;
import ru.innopolis.refrigerator.core.db.jdbc.exception.IngredientDAOException;
import ru.innopolis.refrigerator.core.db.jdbc.exception.IngredientIngredientCategoryDAOException;
import ru.innopolis.refrigerator.core.model.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IngredientDAO {

	private static final Logger logger = LogManager.getLogger(IngredientDAO.class.getName());
	private static IConnectionFactory connection;

	static {
		connection = ConnectionFactoryPostgreSQL.getInstance();
	}

	public static List<Ingredient> getAll() throws IngredientDAOException {
		List<Ingredient> ingredientList = new ArrayList<>();

		try {
			Statement statement = connection.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Ingredient");

			while (resultSet.next()) {
				Ingredient ingredient = new Ingredient(
						resultSet.getString("name"),
						resultSet.getString("dimension"),
						IngredientCategoryDAO.getAllById(IngredientIngredientCategoryDAO.getAllByIngredientId(resultSet.getLong("id"))));
				ingredientList.add(ingredient);
			}
		}catch (IngredientCategoryDAOException e) {
			logger.error("I can not get of all items to the database" + e.toString());
		}
		catch (IngredientIngredientCategoryDAOException e) {
			logger.error("I can not get of all items to the database" + e.toString());
		}
		catch (SQLException e) {
			logger.error("I can not get of all items to the database" + e.toString());
			throw new IngredientDAOException();
		}

		return ingredientList;
	}
}